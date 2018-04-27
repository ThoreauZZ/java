package com.thoreauz.bootlearn.dynamicproxy.cglib;

import com.thoreauz.bootlearn.dynamicproxy.HelloService;

import static org.testng.Assert.*;

/**
 * 2018/4/28 上午10:39.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class CglibDynamicProxyTest {
    public static void main(String[] args) {
        CglibDynamicProxy proxy = new CglibDynamicProxy();
        HelloService helloService = (HelloService)proxy.newProxyInstance(HelloService.class);
        System.out.println(helloService.sayHello());
    }


}