package com.thoreauz.agent;

import com.thoreauz.agent.time.Transformer;

import java.lang.instrument.Instrumentation;

/**
 * The manifest of the agent JAR file must contain the attribute Premain-Class
 * 2019/1/8 11:24 PM.
 *
 * @author thoreauz
 */
public class AgentMain {
    //代理程序入口函数
    public static void premain(String args, Instrumentation inst) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("agent premain begin");
        //添加字节码转换器
        inst.addTransformer(new Transformer(), true);
        System.out.println("agent premain end");
    }
}
