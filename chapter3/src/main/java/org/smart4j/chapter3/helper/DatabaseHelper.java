package org.smart4j.chapter3.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.util.CollectionUtil;
import org.smart4j.chapter3.util.PropsUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 18:26
 * @Description:数据库助手操作类
 **/
public class DatabaseHelper {

    private static final Logger log= LoggerFactory.getLogger (DatabaseHelper.class);

    //数据库操作类
    private static final QueryRunner QUERY_RUNNER;
    //数据库连接池
    private static final BasicDataSource DATA_SOURCE;

    //隔离线程容器
    private static final ThreadLocal<Connection> CONNECTION_HOLDER;
    private static final String DRIVER;

    private static final String URL;

    private static final String USERNAME;

    private static final String PASSWORD;
    static {
        QUERY_RUNNER=new QueryRunner ();

        CONNECTION_HOLDER=new ThreadLocal<> ();

        //获取配置文件
        Properties conf= PropsUtil.loadProps ("config.properties");
        DRIVER=conf.getProperty ("jdbc.driver");
        URL=conf.getProperty ("jdbc.url");
        USERNAME=conf.getProperty ("jdbc.username");
        PASSWORD=conf.getProperty ("jdbc.password");
        //初始化数据库连接池
        //使用数据库连接池不需要使用关闭方法
        DATA_SOURCE=new BasicDataSource ();
        DATA_SOURCE.setDriverClassName (DRIVER);
        DATA_SOURCE.setUrl (URL);
        DATA_SOURCE.setUsername (USERNAME);
        DATA_SOURCE.setPassword (PASSWORD);
    }
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        //获取数据库连接时，先在ThreadLocal中寻找，若不存在，则创建一个新的数据库连接，并将其放入ThreadLocal中，
        Connection conn=CONNECTION_HOLDER.get ();
        if (conn==null){
            try {
                conn= DATA_SOURCE.getConnection ();
            } catch (SQLException e) {
                log.error ("get connection failure",e);
            }finally {
                CONNECTION_HOLDER.set (conn);
            }
        }
        return conn;
    }


    /**
     * 查询实体列表
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static  <T>  List<T>  queryEntityList(Class<T> entityClass,String sql,Object...params){
        List<T> entityList=null;
        try {
            //获取数据库连接
            Connection conn=getConnection ();
            //QueryRunner首先执行sql语句并返回一个Result,随后通过反射去创建并初始化实体对象
            entityList=QUERY_RUNNER.query (conn,sql,new BeanListHandler<T> (entityClass),params);
        } catch (SQLException e) {
            log.error ("query entity list failure",e);
            throw new RuntimeException (e);
        }
        return entityList;
    }
    /**
     * 查询单个实体
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static  <T>  T  queryEntity(Class<T> entityClass,String sql,Object...params){
        T entity=null;
        try {
            //获取数据库连接
            Connection conn=getConnection ();
            //QueryRunner首先执行sql语句并返回一个Result,随后通过反射去创建并初始化实体对象
            entity=QUERY_RUNNER.query (conn,sql,new BeanHandler<> (entityClass),params);
        } catch (SQLException e) {
            log.error ("query entity list failure",e);
            throw new RuntimeException (e);
        }
        return entity;
    }

    /**
     * 执行查询语句
     * @param sql
     * @param params
     * @return
     */
    public static  List<Map<String,Object>>  executeQuery( String sql, Object...params){
       List<Map<String,Object>> result=null;
        try {
            //获取数据库连接
            Connection conn=getConnection ();
            result=QUERY_RUNNER.query (conn,sql,new MapListHandler (),params);
        } catch (SQLException e) {
            log.error ("query entity list failure",e);
            throw new RuntimeException (e);
        }
        return result;
    }

    /**
     * 执行更新语句
     * @param sql
     * @param params
     * @return
     */
    public static int  executeUpdate( String sql, Object...params){
        int rows=0;
        try {
            //获取数据库连接
            Connection conn=getConnection ();
            //QueryRunner执行更新语句，返回更新条数
            rows=QUERY_RUNNER.update (conn,sql,params);
        } catch (SQLException e) {
            log.error ("query entity list failure",e);
            throw new RuntimeException (e);
        }
        return rows;
    }

    /**
     * 插入实体
     * @param entityClass
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty (fieldMap)){
            log.error ("can not insert entity:fieldMap is Empty");
            return false;
        }
        String sql="INSERT INTO "+getTableName(entityClass);
        //字段sql
        StringBuilder columns=new StringBuilder ("(");
        //值sql
        StringBuilder values=new StringBuilder ("(");
        for (String filedName:fieldMap.keySet ()) {
            columns.append (filedName).append (",");
            values.append ("?, ");
        }
        //
        columns.replace (columns.lastIndexOf (","),columns.length (),")");
        values.replace (values.lastIndexOf (","),values.length (),")");
        sql=sql+columns+" VALUES "+ values;
        //生成参数数组
        Object[] params=fieldMap.values ().toArray ();
        return executeUpdate (sql,params)==1;
    }

    /**
     * 根据id更新实体
     * @param entityClass
     * @param id
     * @param fieldMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass,long id,Map<String,Object> fieldMap){
        if(CollectionUtil.isEmpty (fieldMap)){
            log.error ("can not update entity:fieldMap is Empty");
            return false;
        }
        String sql="UPDATE "+getTableName(entityClass)+" SET";
        //字段sql
        StringBuilder columns=new StringBuilder ();
        for (String fieldName:fieldMap.keySet ()) {
            columns.append (fieldName).append ("=?,");
        }
        //将最后一个“，”截掉
        sql=sql+columns.substring (0,columns.lastIndexOf (","))+"WHERE id=?";
        List<Object> paramList=new ArrayList<> ();
        paramList.addAll (fieldMap.values ());
        paramList.add (id);
        Object params=paramList.toArray ();
        return executeUpdate (sql,params)==1;
    }

    /**
     * 根据id删除实体
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */
    public static <T> boolean deleteEntity(Class<T> entityClass,long id){

        String sql="DELETE "+getTableName(entityClass)+" WHERE id=?";
        return executeUpdate (sql,id)==1;
    }

    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName ();
    }

    /**
     * 执行sql文件
     * @param filePath
     */
    public static void exexuteSqlFile(String filePath) {
        InputStream is=Thread.currentThread ().getContextClassLoader ().getResourceAsStream (filePath);
        BufferedReader reader=new BufferedReader (new InputStreamReader (is));
        String sql;
        try {
            while ((sql=reader.readLine ())!=null){
                executeUpdate (sql,null);
            }
        } catch (IOException e) {
            log.error ("execute sql file falure",e);
        }


    }

}
