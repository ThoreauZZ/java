package com.thoreauz.bootlearn.dynamicproxy;

import org.testng.annotations.Test;

import java.lang.reflect.Proxy;

import static org.testng.Assert.*;

/**
 * 2018/4/28 上午10:11.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class HelloServiceTest {
    @Test
    public void testSayHello() {
        HelloService helloService = (HelloService)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
            new Class[] {HelloService.class},
            new DynamicProxyHandler());
        System.out.println("调用结果" + helloService.sayHello());
    }
}