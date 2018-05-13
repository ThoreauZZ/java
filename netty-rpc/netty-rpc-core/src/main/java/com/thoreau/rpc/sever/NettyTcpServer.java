package com.thoreau.rpc.sever;

import com.thoreau.rpc.handler.NettyProtocolHandler;
import com.thoreau.rpc.protocol.Decoder;
import com.thoreau.rpc.protocol.Encoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * 2018/5/11 下午9:25.
 *
 * @author zhaozhou
 */
@Slf4j
public class NettyTcpServer {
    private int port;
    private static HashMap<String, Object> provider = new HashMap<>(1);

    public static HashMap<String, Object> getProvider() {
        return provider;
    }

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("my-rpc-boos"));

    private EventLoopGroup workGroup = new NioEventLoopGroup(
            Runtime.getRuntime().availableProcessors() * 2, new DefaultThreadFactory("my-rpc-worker")
    );

    public NettyTcpServer registerService() {
        try {
            Object obj = Class.forName("com.thoreau.rpc.client.HelloServiceImpl").newInstance();
            Class<?>[] interfaces = obj.getClass().getInterfaces();
            provider.put(interfaces[0].getName(), obj);
        } catch (Exception e) {
            log.info("server init provider obj failed", e);
        }
        return this;
    }

    public NettyTcpServer port(int port) {
        this.port = port;
        return this;
    }

    public void run() {
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                           .channel(NioServerSocketChannel.class)
                           .childHandler(
                                   new ChannelInitializer<NioSocketChannel>() {
                                       @Override
                                       protected void initChannel(NioSocketChannel ch) throws Exception {
                                           ch.pipeline()
                                             .addLast("decode", new Decoder(true))
                                             .addLast("encode", new Encoder())
                                             .addLast("protocolHandler", new NettyProtocolHandler());
                                       }

                                   }
                           );
            ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(this.port)).sync();
            future.syncUninterruptibly();
            System.out.println(" started and listen on " + future.channel()
                                                                 .localAddress());
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println("err--------");
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
