package org.smart4j.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/10/25 0:27
 * @Description:请求转发器
 **/
@WebServlet(urlPatterns = "/*" ,loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    private static final Logger log= LoggerFactory.getLogger (DispatcherServlet.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关Helper类
        HelperLoader.init ();
        //获取ServletContext对象（用于注册Servlet）
        ServletContext servletContext=servletConfig.getServletContext ();
        //注册处理JSP的servlet
        ServletRegistration jspServlet=servletContext.getServletRegistration ("jsp");
        //获取jsp文件路径
        jspServlet.addMapping (ConfigHelper.getAppJspPath ()+"*");
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet=servletContext.getServletRegistration ("default");
        defaultServlet.addMapping (ConfigHelper.getAppAssetPath ()+"*");
    }

    /**
     * 处理逻辑：
     * 1：首先从请求流中获取请求方法和请求路径
     * 2：根据请求方法和请求路径获取Action层处理器
     * 3：从请求流中获取请求请求参数进行封装
     * 4：执行相应的action层方法1
     * 5：获取方法执行结果
     * 6：根据返回结果决定是跳转界面还是返回参数
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求方法与请求路径
        String requestMethod=request.getMethod ().toLowerCase ();
        String requestPath=request.getPathInfo ();
        //获取Action处理器
        Handler handler= ControllerHelper.getHandler (requestMethod,requestPath);
        if (handler!=null){
            //获取Controller类及实例
            Class<?> controllerClass=handler.getControllerClass ();
            Object controller= BeanHelper.getBean (controllerClass);
            //用来存放参数对象
            Map<String,Object> paramMap=new ConcurrentHashMap<> ();
            //创建参数对象名称的map
            Enumeration<String> paramNames=request.getParameterNames ();

            //循环遍历参数名称，从请求流中获取参数值放入参数对象中
            while (paramNames.hasMoreElements ()){
                String paramName=paramNames.nextElement ();
                String paramValue=request.getParameter (paramName);
                paramMap.put (paramName,paramValue);
            }
            //进行编码
            String body= CodeUtil.decodeURL (StreamUtil.getString (request.getInputStream ()));

            if (StringUtil.isNotEmpty (body)){
                String[] params=StringUtil.spiltString(body,"&");
                if (ArrayUtil.isNotEmpty (params)){
                    for (String param:params) {
                        String[] array=StringUtil.spiltString (param,"=");
                        if (ArrayUtil.isNotEmpty (array)&&array.length==2){
                            String paramName=array[0];
                            String paramValue=array[1];
                            paramMap.put (paramName,paramValue);
                        }
                    }
                }
            }

            Param param=new Param (paramMap);
            Method actionMethod=handler.getActionMethod ();

            Object result= ReflectionUtil.invokeMethod (controller,actionMethod,param);

            if (result instanceof View){
                //跳转界面
                View view=(View)result;
                String path=view.getPath ();
                if (path.startsWith ("/")){
                    response.sendRedirect (request.getContextPath ()+path);
                }else {
                    Map<String,Object> model=view.getModel ();
                    for(Map.Entry<String,Object> entry:model.entrySet ()){
                        request.setAttribute (entry.getKey (),entry.getValue ());
                    }
                    request.getRequestDispatcher (ConfigHelper.getAppJspPath ()+path).forward (request,response);
                }
            }else if (result instanceof Data){
                //返回json数据
                Data data=(Data) result;
                Object model=data.getModel ();
                if (model!=null){
                    response.setContentType ("application/json");
                    response.setContentType ("UTF-8");
                    PrintWriter writer=response.getWriter ();
                    String json=JsonUtil.toJson (model);
                    writer.write (json);
                    writer.flush ();
                    writer.close ();
                }
            }

        }else{
            log.error ("no url "+requestPath+"   find");
            throw new RuntimeException ("no url "+requestPath+"find");
        }
    }
}
