package com.thoreau.netty.blockingqueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 2018/5/14 上午10:35.
 *
 * @author zhaozhou
 * @date 2018/05/14
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ConcurrentHashMap<Long, BlockingQueue<Response>> responseMap = new ConcurrentHashMap<>();
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    public Response invoke(Request request) {
        if (ctx != null) {
            Response result = null;
            try {
                ByteBuf buf = Unpooled.buffer();
                byte[] bytes = mapper.writeValueAsBytes(request);
                buf.writeBytes(bytes);
                responseMap.putIfAbsent(request.getRequestId(), new LinkedBlockingQueue<>(1));
                ctx.writeAndFlush(buf);
                result = responseMap.get(request.getRequestId()).poll(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                responseMap.remove(123L);
            }
            return result;
        } else {
            System.out.println("ctx is null");
            return null;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws IOException {
        Response response = mapper.readValue(in.toString(CharsetUtil.UTF_8), Response.class);
        BlockingQueue<Response> queue = responseMap.get(response.getRequestId());
        queue.add(response);
    }
}
