package org.smart4j.chapter1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

    /**
     * 获取系统当前时间，并将其放入HttpServletRequest对象
     * 将请求转发到/WEB-INF/jsp/hello.jsp界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dateFormat=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss:SSS");
        String currentTime=dateFormat.format (new Date ());
        req.setAttribute ("currentTime",currentTime);
        req.getRequestDispatcher ("/WEB-INF/jsp/hello.jsp").forward (req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet (req,resp);
    }
}
