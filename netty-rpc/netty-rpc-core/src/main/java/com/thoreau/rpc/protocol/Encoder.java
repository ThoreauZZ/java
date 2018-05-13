package com.thoreau.rpc.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * 2018/5/12 上午11:28.
 *
 * @author zhaozhou
 */
@Slf4j
public class Encoder extends ChannelOutboundHandlerAdapter {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof RpcContext) {

            Object toDecodeObject;
            RpcContext rpcContext = (RpcContext) msg;
            if (((RpcContext) msg).getResponse() != null) {
                // 有返回值，说明是服务端返回；构造返回Response
                toDecodeObject = rpcContext.getResponse();
            }else {
                toDecodeObject = rpcContext.getRequest();
            }
            byte[] bytes = mapper.writeValueAsBytes(toDecodeObject);


            // 分配buf：长度是 Body+头的长度5
            ByteBuf byteBuf = ctx.alloc().buffer(4 + bytes.length);

            // int = 32bit = 4 byte
            byteBuf.writeInt(bytes.length);

            //body
            byteBuf.writeBytes(bytes);

            log.info(".....json序列化,构造byteBuf......................");
            ctx.writeAndFlush(byteBuf, promise);
        }
    }
}
