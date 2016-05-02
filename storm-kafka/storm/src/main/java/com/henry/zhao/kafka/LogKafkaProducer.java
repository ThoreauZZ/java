package com.henry.zhao.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class LogKafkaProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("metadata.broker.list", "192.168.85.100:9092");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		props.put("zookeeper.session.timeout.ms", "400000");
		ProducerConfig config = new ProducerConfig(props);
		String msg=">>>>>>>>>>>---producer----<<<<<<<<<<<<<<";
		Producer<String, String> producer = new Producer<String, String>(config);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>("foo", msg);
		for (int i = 0; i < 100; i++) {
			producer.send(data);
		}
		producer.close();
	}
}
