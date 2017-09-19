package com.henry;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.33.10:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            long startOne = System.currentTimeMillis();
            Message message = new Message("PushTopic", "tag", "key1", ("Hello RocketMQ "+ i).getBytes());
            SendResult result = producer.send(message);
            System.out.printf("%s%n", result);
            long endOne = System.currentTimeMillis();
            System.out.println("send one msg time: " + (endOne - startOne) + "ms");
        }
        producer.shutdown();

    }
}
