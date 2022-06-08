package com.wang.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class GmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallApplication.class, args);
    }

}
