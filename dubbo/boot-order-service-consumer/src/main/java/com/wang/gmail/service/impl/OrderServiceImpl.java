package com.wang.gmail.service.impl;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:18
 * @Description:
 */

import com.alibaba.dubbo.config.annotation.Reference;
import com.wang.demo.bean.UserAddress;
import com.wang.demo.service.OrderService;
import com.wang.demo.service.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@ClassName OrderServiceImpl
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:18
 @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Reference
    UserService userService;
    @Override
    public List<UserAddress> initOrder(String userID) {
        //查询用户的收货地址
        List<UserAddress> userAddressList = userService.getUserAddressList(userID);

        //为了直观的看到得到的数据，以下内容也可不写
        System.out.println("当前接收到的userId=> "+userID);
        System.out.println("**********");
        System.out.println("查询到的所有地址为：");
        return userAddressList;

    }
}
