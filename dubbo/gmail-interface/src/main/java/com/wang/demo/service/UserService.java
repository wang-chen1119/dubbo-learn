package com.wang.demo.service;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:05
 * @Description:
 */

import com.wang.demo.bean.UserAddress;
import java.util.List;

/**
 *@ClassName UserService
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:05
 @Version 1.0
 */
//用户服务
public interface UserService {
    /**
     * 按照用户id返回所有的收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);
}
