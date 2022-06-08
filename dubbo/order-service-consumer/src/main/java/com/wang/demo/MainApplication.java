package com.wang.demo;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:48
 * @Description:
 */

import com.wang.demo.service.OrderService;
import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@ClassName ConsumerApplication
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:48
 @Version 1.0
 */
public class MainApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        //调用方法查询出数据
        orderService.initOrder("1");
        System.out.println("调用完成...");
        System.in.read();
    }
}