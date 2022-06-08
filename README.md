# dubbo-learn
dubbo学习总结，简单实例代码

# RPC

RPC ： Remote Procedure Call Protocol 远程过程调用模型

允许一个程序可以调用另一个地址空间的函数或者过程

![img](https://image-1305623006.cos.ap-nanjing.myqcloud.com/img/202206081456163.png)

# dubbo

### dubbo的优势

- 面向接口代理的高性能RPC调用
- 服务自动注册与发现
- 运行期流量调度
- 智能负载均衡
- 高度可扩展能力
- 可视化的服务治理与运维

### dubbo的架构



![img](https://image-1305623006.cos.ap-nanjing.myqcloud.com/img/202206081456808.png)

### 注册中心

服务提供者启动后会将自己注册到注册中心中，消费者启动后会从注册中心中去获取服务，同时保持长链接，当服务提供者发生变化时，注册中心会将服务推送给消费者。

### 可视化监控

进入GitHub https://github.com/apache/dubbo-admin

**生产设置**

1. 在开发分支上克隆源代码`git clone https://github.com/apache/dubbo-admin.git`
2. 在中指定注册地址`dubbo-admin-server/src/main/resources/application.properties`
3. 建造
   - `mvn clean package -Dmaven.test.skip=true`
4. 开始
   - `mvn --projects dubbo-admin-server spring-boot:run` 或者
   - `cd dubbo-admin-distribution/target`; `java -jar dubbo-admin-0.1.jar`
   - dubbo-admin\dubbo-admin-server\target中找到Jar包 `java -jar dubbo-admin-0.1.jar`
5. 访问`http://localhost:8080`
6. 默认用户名和密码是`root`

**开发设置**

- 运行管理服务器项目

  backend 是一个标准的 spring boot 项目，你可以在任何 java IDE 中运行它

- 运行管理 ui 项目

  首先 npm intsall

  运行`npm run dev`。

- 访问网页

  访问`http://localhost:8081`，前端支持热重载。

## 编码实践

#### 未整合springboot

**需要引入的依赖**

<!--dubbo-->

<dependency>

<groupId>com.alibaba</groupId>

<artifactId>dubbo</artifactId>

<version>2.6.2</version>

</dependency>

<!--注册中心是 zookeeper，引入zookeeper客户端-->

<dependency>

<groupId>org.apache.curator</groupId>

<artifactId>curator-framework</artifactId>

<version>2.12.0</version>

</dependency>

**服务提供者xml文件配置**

<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"

xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd

http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

<!--1、指定当前服务/应用的名字(同样的服务名字相同，不要和别的服务同名)-->

<dubbo:application name="user-service-provider"></dubbo:application>

<!--2、指定注册中心的位置-->

<!--<dubbo:registry address="zookeeper://127.0.0.1:2181"></dubbo:registry>--> <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>

<!--3、指定通信规则（通信协议? 服务端口）-->

<dubbo:protocol name="dubbo" port="20881"></dubbo:protocol>

<!--4、暴露服务 让别人调用 ref指向服务的真正实现对象-->

<dubbo:service interface="com.wang.demo.service.UserService" ref="userServiceImpl"></dubbo:service>

<!--服务的实现-->

<bean id="userServiceImpl" class="com.wang.demo.service.impl.UserServiceImpl"></bean>

</beans>

**启动类**

public class MailApplication {

public static void main(String[] args) throws IOException {

```
ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("provider.xml");

applicationContext.start();

System.in.read();
```

}

}

**服务消费者**

<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"

xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"

xmlns:context="http://www.springframework.org/schema/context"

xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd

http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd http://dubbo.apache.org/schema/dubbo http://dubbo.apache.org/schema/dubbo/dubbo.xsd http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">

<!--包扫描-->

<context:component-scan base-package="com.wang.demo.service.impl"/>

<!--指定当前服务/应用的名字(同样的服务名字相同，不要和别的服务同名)-->

<dubbo:application name="order-service-consumer"></dubbo:application>

<!--指定注册中心的位置-->

<dubbo:registry address="zookeeper://127.0.0.1:2181"></dubbo:registry>

<!--调用远程暴露的服务，生成远程服务代理-->

<dubbo:reference interface="com.wang.demo.service.UserService" id="userService"></dubbo:reference>

</beans>

**启动类**

public class MainApplication {

public static void main(String[] args) throws IOException {

```
ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");  
```

OrderService orderService = applicationContext.getBean(OrderService.class);

//调用方法查询出数据

orderService.initOrder("1");

System.out.println("调用完成...");

System.in.read();

}

}

#### 整合springboot

**服务消费者application.properties配置**(直接配置文件中声明注册地址，应用名称等信息)

dubbo.application.name=boot-order-service-consumer
 dubbo.registry.address=zookeeper://127.0.0.1:2181
 dubbo.monitor.protocol=register

通过** @Reference注解来调用暴露的服务**

最新版本注解为DubboReference

**@EnableDubbo**开启通过注解使用dubbo

服务提供者**application.properties配置**

dubbo.registry.address=127.0.0.1:2181

dubbo.registry.protocol=zookeeper

dubbo.protocol.name=dubbo

dubbo.protocol.port=20880

\#连接监控中心

dubbo.monitor.protocol=registry

通过**import com.alibaba.dubbo.config.annotation.Service;注解来暴露服务**

最新版本注解为DubboService

## 配置

### dubbo支持的配置来源

- JVM System Properties，JVM -D 参数
- System environment，JVM进程的环境变量
- Externalized Configuration，外部化配置，从配置中心读取
- Application Configuration，应用的属性配置，从Spring应用的Environment中提取"dubbo"打头的属性集
- API / XML /注解等编程接口采集的配置可以被理解成配置来源的一种，是直接面向用户编程的配置采集方式
- 从classpath读取配置文件 dubbo.properties

**覆盖顺序**

![img](https://image-1305623006.cos.ap-nanjing.myqcloud.com/img/202206081456912.png)

**配置路程**

![img](https://image-1305623006.cos.ap-nanjing.myqcloud.com/img/202206081455751.png)