package com.thoreau.rpc.client;

import com.thoreau.rpc.proxy.DynamicProxyHandler;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 2018/5/12 上午2:11.
 *
 * @author zhaozhou
 */
public class NettyTcpClientTest {
    @Test
    public void testSayHello() {
        HelloService helloService = (HelloService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[] {HelloService.class},
                new DynamicProxyHandler());
        System.out.println("调用结果" + helloService.sayHello());
    }
}