package com.thoreau.netty.start.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 17/8/22 下午8:11.
 *
 * @author zhaozhou
 */
@ChannelHandler.Sharable//标示一个 Channel- Handler 可以被多 个 Channel 安全地 共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bytebuf = (ByteBuf) msg;
        System.out.println("Server received:"+ bytebuf.toString(CharsetUtil.UTF_8));
        ctx.write(bytebuf);// 收到的消息写给发送者
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点,并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();// 打印异常
        ctx.close();// 关闭连接
    }
}
