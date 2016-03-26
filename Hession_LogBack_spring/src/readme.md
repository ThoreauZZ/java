##hessian
###hessian是什么？
1.  Hessian 是一个基于 binary-RPC 实现的远程通讯 library。使用二进制传输数据。
Hessian通常通过Web应用来提供服务，通过接口暴露。Servlet和Spring的DispatcherServlet都可以把请求转发给Hessian服务。
由以下两种方式提供，分别为：com.caucho.hessian.server.HessianServlet、org.springframework.web.servlet.DispatcherServlet。

###关于hessian的7个问题：

1. 是基于什么协议实现的？
 	基于Binary-RPC协议实现。

2、怎么发起请求？
	需通过Hessian本身提供的API来发起请求。

3、怎么 将请求转化为符合协议的格式的？
	Hessian通过其自定义的串行化机制将请求信息进行序列化，产生二进制流。

4、使用什么 传输协议传输？
	Hessian基于Http协议进行传输。

5、响应端基于什么机制来接收请求？
	响应端根据Hessian提供的API来接收请求。

6、怎么将流还原为传输格式的？
	Hessian根据其私有的串行化机制来将请求信息进行反序列化，传递给使用者时已是相应的请求信息对象了。

7、处理完毕后怎么回应？
	处理完毕后直接返回，hessian将结果对象进行序列化，传输至调用端。
hessian的优缺点

###优缺点
优点：  

    简单易用，面向接口，通过接口暴露服务，jar包只有200、300k，不需要配置防火墙
效率高，复杂对象序列化速度仅次于RMI，简单对象序列化优于RMI，二进制传输
多语言支持：wiki、Java、Flash/Flex、Python、C++、.NET C#、PHP、Ruby、Objective-C
可与spring集成，配置简单，HessianServiceExporte

 

缺点：  
	缺乏安全机制，传输没有加密处理 异常机制不完善，总是报一些错误，错误原因也是千奇百怪，
	提示信息不足事务处理欠缺版本问题，spring 2.5.6对照3.1.3版，spring 3对照4.0及以上版本，
	需要使用spring MVC部分内容
	
###hessian开发配置
1. jar包：spring，aopalliance,hessian（注意：spring和hessian的兼容问题）
2. 写借口和实现类
3. 配置web.xml
4. 配置hessian-servlet.xml
5.写客户端访问http://localhost:8080/Hessionweb/hessian/hello
详细请见文件注释；

##logback
###加载步骤
1. Logback tries to find a file called logback.groovy in the classpath.
2. If no such file is found, logback tries to find a file called logback-test.xml in the classpath.
3. If no such file is found, it checks for the file logback.xml in the classpath..
4. If no such file is found, and the executing JVM has the ServiceLoader (JDK 6 and above) the ServiceLoader will be used to resolve an implementation of com.qos.logback.classic.spi.Configurator. The first implementation found will be used. See ServiceLoader documentation for more details.
5. If none of the above succeeds, logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.

###如果想在界面上看到日志：

````
<servlet>
    <servlet-name>ViewStatusMessages</servlet-name>
    <servlet-class>ch.qos.logback.classic.ViewStatusMessagesServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>ViewStatusMessages</servlet-name>
    <url-pattern>/lbClassicStatus</url-pattern>
  </servlet-mapping>
````
###详细见logback.xml


####log4j也可以动态读取配置
<!-- 让web应用程序启动那个时自动添加属性文件 -->
	<context-param>
		<param-name>log4jConfigLocation</param-name>
		<param-value>classpath:log4j.properties</param-value>
	</context-param>
	<!-- 以Listener方式启动LOG4j -->
	<listener>
		<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
</listener>
  
 
###SLF4J 是一个日志框架，支持log4j和logback，强大的日志记录方式；
示例：
package com.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class testSelf4j {
	private static Logger logger = LoggerFactory.getLogger(testSelf4j.class);
	private static String name;
	private static int age;
	private static String address;
	public static void main(String[] args) {
		age = 22;        
		name = "Henry";
		try {
		address.length();
		logger.debug(" 名字是--{}. 年龄是 {}.", name, age);
		
			throwExc();
		} catch (Exception e) {
			logger.error("---异常----"+'\n'+"位置---{}，"+'\n'+"原因----{},---",e.getStackTrace(),e.toString());
		}
	}
	public static void throwExc() throws Exception{
		System.out.println("----hhhhh---");
	}
}
使用步骤：
添加jar包：支持logback和log4j。
        
<dependency> 
  <groupId>ch.qos.logback</groupId>
  <artifactId>logback-classic</artifactId>
  <version>1.0.13</version>
</dependency>
<dependency> 
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.13</version>
</dependency>
在类里面使用：private static Logger logger = LoggerFactory.getLogger(testSelf4j.class);
可以用{}打印变量信息
logger.error("---异常----"+'\n'+"位置---{}，"+'\n'+"原因----{},---",e.getStackTrace(),e.toString());

 
