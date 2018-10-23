package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author: Gentleman
 * @Date: 2018/10/21 23:48
 * @Description:类操作工具类
 **/
public final class ClassUtil {

    private static final Logger log= LoggerFactory.getLogger (ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoder(){
        //获取当前线程的ClassLoder
        return Thread.currentThread ().getContextClassLoader ();
    }

    /**
     * 加载类：根据类名和是否初始化加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            cls=Class.forName (className,isInitialized,getClassLoder ());
        } catch (ClassNotFoundException e) {
            log.error ("load class failure",e);
            throw new RuntimeException (e);
        }
        return cls;
    }
    /**
     * 获取指定包名下的所有类:根据包名将其转换为文件路径，读取该文件路径下的class文件或jar包，获取指定的类名去加载类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet=new HashSet<> ();
        try {
            //Enumeration:枚举类：只提供了遍历Vector和HashTable类型集合元素的功能
            //boolean hasMoreElements();//判断是否包含元素
            //E nextElement();//获得下一个元素

            // 定义一个枚举的集合 并进行循环来处理这个目录下的things
            Enumeration<URL> urls=getClassLoder ().getResources (packageName.replace (".", File.separator));
            while (urls.hasMoreElements ()){
                URL url=urls.nextElement ();
                if (url!=null){
                    // 得到协议的名称
                    String protocol=url.getProtocol ();
                    // 如果是以文件的形式保存在服务器上
                    if (protocol.equals ("file")){
                        // 获取包的物理路径
                        // 20%是URL的空格，将其代替，SUN公司也说明了这是一个BUG
                        String packagePath=url.getPath ().replaceAll ("%20","");
                        //解决路径包含中文的情况
                        packagePath= java.net.URLDecoder.decode(packagePath,"utf-8");
                        addClass(classSet,packagePath,packageName);
                    }else if (protocol.equals ("jar")){
                        //如果是Jar则建立连接
                        JarURLConnection jarURLConnection=(JarURLConnection)url.openConnection ();
                        if (jarURLConnection!=null){
                            JarFile jarFile=jarURLConnection.getJarFile ();
                            if (jarFile!=null){
                                //得到jar文件内的实体
                                Enumeration<JarEntry> jarEntryEntries=jarFile.entries ();
                                while (jarEntryEntries.hasMoreElements ()){
                                    JarEntry jarEntry=jarEntryEntries.nextElement ();
                                    //得到jar实体的名字 例如javax/crypto/SecretKey.class
                                    String jarEntryName=jarEntry.getName ();
                                    if (jarEntryName.endsWith (".class")){
                                        String className=jarEntryName.substring (0,jarEntryName.lastIndexOf (".".replaceAll (File.separator,".")));
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error ("get class set failure",e);
            throw new RuntimeException (e);
        }
        return classSet;
    }

    /**
     * 将目录下的所有子类进行添加到classSet中
     * @param classSet
     * @param packagePath
     * @param packageName
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
            File[] files=new File(packagePath).listFiles (new FileFilter () {
                @Override
                public boolean accept(File file) {
                    return (file.isFile ()&&file.getName ().endsWith (".class"))||file.isDirectory ();
                }
            });
        for (File file:files) {
            String fileName=file.getName ();
            if(file.isFile ()){
                String className=fileName.substring (0,fileName.lastIndexOf ("."));
                if (StringUtil.isNotEmpty (packageName)){
                    className=packageName+"."+className;
                }
                doAddClass (classSet,className);
            }else {
                String subPackagePath=fileName;
                if (StringUtil.isNotEmpty (packagePath)){
                    subPackagePath=packagePath+"/"+subPackagePath;
                }
                String subPackageName=fileName;
                if (StringUtil.isNotEmpty (packageName)){
                    subPackageName=packageName+"."+subPackageName;
                }
                addClass (classSet,subPackagePath,subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
            Class<?> cls=loadClass (className,false);
        classSet.add (cls);
    }


}
