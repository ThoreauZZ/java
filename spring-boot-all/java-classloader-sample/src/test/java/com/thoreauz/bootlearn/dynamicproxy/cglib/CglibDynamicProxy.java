package com.thoreauz.bootlearn.dynamicproxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 2018/4/28 上午10:37.
 *
 * @author zhaozhou
 * @date 2018/04/28
 */
public class CglibDynamicProxy {
    private final Enhancer enhancer = new Enhancer();

    public Object newProxyInstance(Class interfaceClass) {
        enhancer.setSuperclass(interfaceClass);
        //设置回调实例
        enhancer.setCallbacks(new MethodInterceptor[] {(o, method, objects, methodProxy) -> {
            System.out.println("初始化rpc请求：" + method.getDeclaringClass().getName() + "#" + method.getName());
            return "ok";
        }});
        return enhancer.create();
    }
}
