package com.thoreau.rpc.client;

import com.thoreau.rpc.protocol.RpcContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 2018/5/11 下午10:36.
 *
 * @author zhaozhou
 */
@Slf4j
public class DefaultClientHandler extends SimpleChannelInboundHandler<RpcContext> {
    private volatile Channel channel;
    private  CountDownLatch countDownLatch;

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("handler active：可以发送数据............");
        super.channelActive(ctx);
        channel = ctx.channel();
    }



    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        channel = ctx.channel();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcContext rpcContext) throws Exception {
        log.info("服务端返回值：" + rpcContext);
        countDownLatch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.info("出现异常，可以在这儿实现重试逻辑");
        ctx.close();
    }

    public void invoke(RpcContext rpcCtx,CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        log.info("调用channel发送数据{},{}",channel,rpcCtx);
        channel.writeAndFlush(rpcCtx);
    }
}
