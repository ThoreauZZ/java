package com.thoreau.spring.stream.springcloudstream.web;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


@EnableBinding(Sink.class)
public class Consumer {
    @StreamListener(Sink.INPUT)
    public void loggerSink(String msg) {
        System.out.println("Revive Msg: {"+msg+"}");
    }
}
