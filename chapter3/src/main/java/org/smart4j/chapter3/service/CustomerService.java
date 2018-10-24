package org.smart4j.chapter3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.framework.annotation.Service;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务:最初始版本
 */
@Service
public class CustomerService {

    private static final Logger log= LoggerFactory.getLogger (CustomerService.class);

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList(){
        //查询sql
        String sql="SELECT * FROM CUSTOMER";
        return DatabaseHelper.queryEntityList (Customer.class,sql,null);
    }
    /**
     * 获取客户
     */
    public Customer getCustomer(long id){
        //查询sql
        String sql="SELECT * FROM CUSTOMER WHERE ID= ?";
        return DatabaseHelper.queryEntity (Customer.class,sql,id);
    }

    /**
     * 创建客户
     * @return
     */
    public boolean createCustomer(Map<String,Object> fieldMap){
        return DatabaseHelper.insertEntity (Customer.class,fieldMap);
    }
    /**
     * 更新客户
     * @return
     */
    public boolean updateCustomer(long id,Map<String,Object> fieldMap){
        return DatabaseHelper.updateEntity (Customer.class,id,fieldMap);
    }
    /**
     * 删除
     * @return
     */
    public boolean deleteCustomer(long id){
        return DatabaseHelper.deleteEntity (Customer.class,id);
    }

}
