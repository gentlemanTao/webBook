package org.smart4j.chapter2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Gentleman
 * @Date: 2018/10/19 22:27
 * @Description:进入客户列表界面
 **/
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet{

    private  static final Logger log= LoggerFactory.getLogger (CustomerServlet.class);

    private CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList=customerService.getCustomerList ();
        req.setAttribute ("customerList",customerList);
        req.getRequestDispatcher ("/WEB-INF/view/customer.jsp").forward (req,resp);
    }

    /**
     * 初始化servlet的时候：初始化CustomerService
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        log.info ("CustomerServlet...init");
        customerService=new CustomerService ();
    }
}
