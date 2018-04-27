package com.thoreauz.bootlearn.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 2018/4/28 上午10:10.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class DynamicProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke 方法中处理rpc序列化、网络调用等");
        return "Hello";
    }
}
