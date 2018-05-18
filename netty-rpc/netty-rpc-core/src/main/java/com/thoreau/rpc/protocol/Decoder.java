package com.thoreau.rpc.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 2018/5/12 上午11:28.
 *
 * @author zhaozhou
 */
@Slf4j
public class Decoder extends ByteToMessageDecoder {
    private static final ObjectMapper jsonMapper = new ObjectMapper();

    private boolean isRequest;

    public Decoder(boolean isRequest) {
        this.isRequest = isRequest;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() == 0) {
            ctx.close();
        }
        // 获取读索引
        int readerIndex = in.readerIndex();
        int bodyLength =  in.getInt(readerIndex);
        byte[] body = new byte[bodyLength];
        in.getBytes(readerIndex + 4, body);
        RpcContext rpcContext = new RpcContext();
        if (isRequest) {
            Request res = jsonMapper.readValue(body, Request.class);
            rpcContext.setRequest(res);
        }else {
            Response res = jsonMapper.readValue(body, Response.class);
            rpcContext.setResponse(res);
        }
        in.skipBytes(in.readableBytes());
        out.add(rpcContext);
    }
}
