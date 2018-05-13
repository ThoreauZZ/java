package com.thoreau.rpc.client;

/**
 * 2018/5/12 上午2:12.
 *
 * @author zhaozhou
 */
public class HelloServiceImpl implements HelloService {
   public String sayHello() {
        return "hello client";
    }
}
