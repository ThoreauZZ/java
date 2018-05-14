package com.thoreau.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 2018/5/14 上午10:28.
 *
 * @author zhaozhou
 * @date 2018/05/14
 */
public class NettyClient {
    private static EventLoopGroup group = new NioEventLoopGroup(1);

    public static ChannelHandler getHandler() throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(group)
         .channel(NioSocketChannel.class)
         .handler(new ChannelInitializer<SocketChannel>() {
             @Override
             protected void initChannel(SocketChannel ch) throws Exception {
                 ch.pipeline().addLast(new ClientHandler());
             }
         });
        ChannelFuture f = b.connect("127.0.0.1", 8080).sync();
        Channel channel = f.sync().channel();
        return channel.pipeline().get(ClientHandler.class);
    }
    public static void shutDown() {
        group.shutdownGracefully();
    }
}
