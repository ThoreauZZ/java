package com.thoreau.server;

import com.thoreau.rpc.sever.NettyTcpServer;
import org.junit.Test;

/**
 * 2018/5/13 下午11:52.
 *
 * @author zhaozhou
 */
public class BootStrapTest {
    @Test
    public void run() {
        new NettyTcpServer()
                .registerService()
                .port(8888)
                .run();
    }
}
