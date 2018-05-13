package com.thoreau.rpc.sever;

/**
 * 2018/5/11 下午9:58.
 *
 * @author zhaozhou
 */
public class BootStrap {
    public static void main(String[] args) {
        new NettyTcpServer()
                .registerService()
                .port(8888)
                .run();
    }
}
