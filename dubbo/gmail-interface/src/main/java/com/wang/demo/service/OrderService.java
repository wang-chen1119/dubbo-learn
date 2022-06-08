package com.wang.demo.service;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:17
 * @Description:
 */

import com.wang.demo.bean.UserAddress;
import java.util.List;

/**
 *@ClassName orderService
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:17
 @Version 1.0
 */
public interface OrderService {
    /**
     * 初始化订单
     * @param userID
     */
     List<UserAddress> initOrder(String userID);
}