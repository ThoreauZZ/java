package com.thoreau.rpc.client;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * 2018/5/11 下午10:35.
 *
 * @author zhaozhou
 */
public class NettyTcpClient {

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("my-rpc-client"));

    public EventLoopGroup getEventLoopGroup() {
        return eventLoopGroup;
    }

    private static volatile NettyTcpClient instance;

    /**
     * 为什么不建议用双重检查？
     *
     * @return NettyTcpClient
     */
    public static NettyTcpClient getInstance() {
        if (instance == null) {
            synchronized (NettyTcpClient.class) {
                if (instance == null) {
                    instance = new NettyTcpClient();
                }
            }
        }
        return instance;
    }

}
