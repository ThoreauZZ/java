package com.thoreau.rpc.handler;

import com.thoreau.rpc.protocol.Request;
import com.thoreau.rpc.protocol.Response;
import com.thoreau.rpc.protocol.RpcContext;
import com.thoreau.rpc.sever.NettyTcpServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * 2018/5/11 下午9:40.
 *
 * @author zhaozhou
 */
@Slf4j
public class NettyProtocolHandler extends SimpleChannelInboundHandler<RpcContext>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcContext rpcContext) throws Exception {
        if (rpcContext == null) {
            return;
        }
        Request req = rpcContext.getRequest();
        Response res= new Response();

        //copy properties
        res.setSeqNum(req.getSeqNum());
        res.setObjName(req.getObjName());
        res.setMethodName(req.getMethodName());

        Class[] argTypes = null;
        Object[] args = req.getArgs();
        if (args != null) {
            argTypes = new Class[args.length];
            for(int i=0;i<args.length;i++){
                argTypes[i] = args[i].getClass();
            }
        }


        Object obj= NettyTcpServer.getProvider().get(req.getObjName());
        Class clazz= obj.getClass();

        Method method = clazz.getMethod(req.getMethodName(), argTypes);
        Object result= method.invoke(obj, req.getArgs());
        res.setResult(result);
        rpcContext.setResponse(res);

        log.info("服务端写返回值");
        ctx.writeAndFlush(rpcContext);
    }
}
