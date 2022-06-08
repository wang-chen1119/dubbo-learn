package com.wang.demo;
/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Tom
 * @Date: 2022/06/06/17:31
 * @Description:
 */

import java.io.IOException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *@ClassName MailApplication
 *@Description
 *@Author 王琛
 *@Date 2022/6/6 17:31
 @Version 1.0
 */
public class MailApplication {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("provider.xml");
        applicationContext.start();
        System.in.read();
    }
}
