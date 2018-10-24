package org.smart4j.chapter3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.chapter3.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 22:27
 * @Description:进入客户列表界面
 **/
@Controller
public class CustomerServlet {

    private  static final Logger log= LoggerFactory.getLogger (CustomerServlet.class);

    @Inject
    private CustomerService customerService;

    @Action ("get:/customer")
    public View index(Param param){
        List<Customer> customerList=customerService.getCustomerList ();
        return new View ("customer.jsp").addModel ("customerList",customerList);
    }
}
