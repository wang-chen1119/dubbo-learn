package com.wang.gmall.service.impl;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:18
 * @Description:
 */


import com.alibaba.dubbo.config.annotation.Service;
import com.wang.demo.bean.UserAddress;
import com.wang.demo.service.UserService;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;


/**
 *@ClassName OrderServiceImpl
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:18
 @Version 1.0
 */
@Service
@Component
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {

        UserAddress address1 = new UserAddress(1, "河南省郑州巩义市宋陵大厦2F", "1", "安然", "150360313x", "Y");
        UserAddress address2 = new UserAddress(2, "北京市昌平区沙河镇沙阳路", "1", "情话", "1766666395x", "N");

        return Arrays.asList(address1,address2);
    }
}
