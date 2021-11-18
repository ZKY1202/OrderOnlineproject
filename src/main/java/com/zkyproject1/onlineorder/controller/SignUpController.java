package com.zkyproject1.onlineorder.controller;

import org.springframework.stereotype.Controller;
import com.zkyproject1.onlineorder.entity.Customer;
import com.zkyproject1.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class SignUpController {

    @Autowired
    private CustomerService customerService;

    //定义 REST API
    // Use the @RequestMapping annotation to define REST API, such as HTTP URL, method, etc.
    @RequestMapping(value = "/signup", method = RequestMethod.POST) // REST api -> URL : signup;   method: post
    @ResponseStatus(value = HttpStatus.CREATED) // 操作成功后，返回一个201 表示成功；

    public void signUp(@RequestBody Customer customer) {
        // convert RequestBody in the http request to a backend object
        // For example,
        //{
        //    "firstName": "xxx",
        //    "lastName": "yyy",
        //    "email": "stafanlaioffer@gmail.com",
        //    "password": "111"
        //}
        customerService.signUp(customer);

    }


}
