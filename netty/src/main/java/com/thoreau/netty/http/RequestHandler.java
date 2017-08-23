package com.thoreau.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.CONTINUE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;
import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 17/8/23 下午8:30.
 *
 * @author zhaozhou
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof DefaultHttpRequest) {
            HttpRequest req = (DefaultHttpRequest) msg;
            if (is100ContinueExpected(req)) {
                ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
            }
            //language=JSON
            String resp = "{\"name\":\"thoreau\",\"age\":26}";
            FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(
                    resp.getBytes(CharsetUtil.UTF_8)));
            res.headers().set(CONTENT_TYPE, "application/json");
            res.headers().set(CONTENT_LENGTH, res.content().readableBytes());
            if (isKeepAlive(req)) {
                res.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
                ctx.write(res);
            } else {
                ctx.write(res).addListener(ChannelFutureListener.CLOSE);
            }

        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
