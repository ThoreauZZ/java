package com.thoreau.rpc.proxy;

import com.thoreau.rpc.client.DefaultClientHandler;
import com.thoreau.rpc.client.NettyTcpClient;
import com.thoreau.rpc.protocol.Decoder;
import com.thoreau.rpc.protocol.Encoder;
import com.thoreau.rpc.protocol.Request;
import com.thoreau.rpc.protocol.RpcContext;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 2018/5/12 上午12:19.
 *
 * @author zhaozhou
 */
@Slf4j
public class DynamicProxyHandler implements InvocationHandler {

    private Map<InetSocketAddress, DefaultClientHandler> connectedServerNodes = new ConcurrentHashMap<>();


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("1.构建请求...........");
        RpcContext context = createRpcContext(proxy, method, args);

        log.info("2. 获取连接...........");
        DefaultClientHandler clientHandler = connectAndGetHandler(new InetSocketAddress("127.0.0.1", 8888));

        log.info("3. 发起调用.............");

        CountDownLatch countDownLatch = new CountDownLatch(1);
        clientHandler.invoke(context,countDownLatch);
        countDownLatch.await();
        // 因为 clientHandler.invoke 是异步调用，先sleep等待
        return "Hello";
    }

    private RpcContext createRpcContext(Object proxy, Method method, Object[] args) {
        RpcContext rpcContext = new RpcContext();
        HashMap<String, Object> attributes = new HashMap<>(10);
        Request request = new Request();
        request.setSeqNum(123);
        request.setArgs(args);
        request.setMethodName(method.getName());
        request.setObjName("com.thoreau.rpc.client.HelloService");
        rpcContext.setAttributes(attributes);
        rpcContext.setRequest(request);
        return rpcContext;
    }

    private void connect(final InetSocketAddress remotePeer) {
        doConnect(null, remotePeer, 0, true);
    }

    private DefaultClientHandler connectAndGetHandler(final SocketAddress address) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(NettyTcpClient.getInstance().getEventLoopGroup())
         .channel(NioSocketChannel.class)
         .handler(new ChannelInitializer<SocketChannel>() {
             @Override
             protected void initChannel(SocketChannel ch) throws Exception {
                 ch.pipeline().addLast("Decoder", new Decoder(false));
                 ch.pipeline().addLast("Encoder", new Encoder());
                 ch.pipeline().addLast("default", new DefaultClientHandler());
             }
         });
        ChannelFuture channelFuture = b.connect(address).sync();
        DefaultClientHandler defaultClientHandler = channelFuture.channel().pipeline().get(DefaultClientHandler.class);
        return defaultClientHandler;
    }
    /**
     * 1. 客户端EventLoop使用很重要；
     *
     * @param channel
     * @param remotePeer
     * @param delay
     */
    private void doConnect(final Channel channel, final SocketAddress remotePeer, long delay, boolean isSync) {
        NettyTcpClient.getInstance().getEventLoopGroup().schedule(() -> {
            createNettyHandler(channel, remotePeer);
        }, delay, TimeUnit.MILLISECONDS);
    }

    private void createNettyHandler(Channel channel, SocketAddress remotePeer) {
        ChannelFuture channelFuture;
        try {
            Bootstrap b = new Bootstrap();
            b.group(NettyTcpClient.getInstance().getEventLoopGroup())
             .channel(NioSocketChannel.class)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast("Encoder", new Encoder());
                     ch.pipeline().addLast("default", new DefaultClientHandler());
                 }
             });
            channelFuture = b.connect(remotePeer);
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    log.warn("Can't connect to remote server: {}", remotePeer.toString());
                    reconnect(future.channel(), remotePeer);
                } else {
                    log.info("Successfully connect to remote server: {}", remotePeer.toString());
                    putTwoHandlerList((InetSocketAddress) remotePeer, future);
                }
            });
        } catch (Exception e) {
            log.warn("Connect got exception: ", e);
            reconnect(channel, remotePeer);
        }
    }

    private void putTwoHandlerList(InetSocketAddress remotePeer, ChannelFuture future) {
        DefaultClientHandler oneHandler = future.channel()
                                                .pipeline()
                                                .get(DefaultClientHandler.class);
        connectedServerNodes.put(remotePeer, oneHandler);
    }

    private void reconnect(Channel channel, SocketAddress remotePeer) {
        doConnect(channel, remotePeer, 3000, true);
    }
}
