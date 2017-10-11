package com.thoreau.spring.stream.springcloudstream.web;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class Producer {

    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
    public MessageSource<String> send() {
        return MessageBuilder.withPayload("Random-:" +  new Random().nextInt( 10 ))::build;
    }
}
