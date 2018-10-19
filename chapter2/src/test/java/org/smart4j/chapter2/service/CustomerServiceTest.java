package org.smart4j.chapter2.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.chapter2.helper.DatabaseHelper;
import org.smart4j.chapter2.model.Customer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CustomerService单元测试
 */
public class CustomerServiceTest {

   private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService ();
    }

   @Before
   public void init(){
        //初始化数据库
       DatabaseHelper.exexuteSqlFile ("sql/customer_init.sql");
   }
   @Test
   public void getCustomerListTest(){
        List<Customer> customerList=customerService.getCustomerList ();
       Assert.assertEquals (2,customerList.size ());
   }

    @Test
    public void getCustomerTest(){
        long id=1;
        Customer customer=customerService.getCustomer (id);
        Assert.assertNotNull (customer);
    }
    @Test
    public void createCustomerTest(){
       Map<String,Object> fieldMap=new ConcurrentHashMap<> ();
       fieldMap.put ("name","customer100");
       fieldMap.put ("contace","John");
       fieldMap.put ("telephone","13512345678");
       boolean result=customerService.createCustomer (fieldMap);
       Assert.assertTrue (result);
    }
    @Test
    public void updateCustomerTest(){
        long id=1;
        Map<String,Object> fieldMap=new ConcurrentHashMap<> ();
        fieldMap.put ("contace","Eric");
        boolean result=customerService.updateCustomer (id,fieldMap);
        Assert.assertTrue (result);
    }
    @Test
    public void deleteCustomerTest(){
        long id=1;
        boolean result=customerService.deleteCustomer (id);
        Assert.assertTrue (result);
    }
}
