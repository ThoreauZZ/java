package com.henry.zhao;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {
	public static void main(String[] args) {
		DefaultMQProducer producer = new DefaultMQProducer("Producer");
		producer.setNamesrvAddr("192.168.217.100:9876");
		try {
			producer.start();
			Message message = new Message("PushTopic", "tag", "key1", "it my mesage".getBytes());
			SendResult result = producer.send(message);
			System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
