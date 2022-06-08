package com.wang.gmail.controller;

import com.wang.demo.bean.UserAddress;
import com.wang.demo.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *@ClassName OrderController
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 20:47
 @Version 1.0
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/initOrder")
    @ResponseBody
    public List<UserAddress> initOrder(@RequestParam("Uid") String userId) {
        return orderService.initOrder(userId);
    }

}
