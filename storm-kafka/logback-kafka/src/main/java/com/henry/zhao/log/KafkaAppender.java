package com.henry.zhao.log;

import java.util.Properties;

import com.henry.zhao.log.formatter.Formatter;
import com.henry.zhao.log.formatter.MessageFormatter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaAppender extends AppenderBase<ILoggingEvent> {
	private String topic;
	private String brokerList;
	private Producer<String, String> producer;
	private Formatter formatter;

	@Override
	public void start() {
		if (this.formatter == null) {
			this.formatter = new MessageFormatter();
		}
		super.start();
		Properties props = new Properties();
		props.put("metadata.broker.list", brokerList);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);
		producer = new Producer<String, String>(config);
	}

	@Override
	public void stop() {
		super.stop();
		this.producer.close();
	}

	@Override
	protected void append(ILoggingEvent event) {
		String payload = this.formatter.format(event);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic,payload);
		producer.send(data);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	

	public String getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(String brokerList) {
		this.brokerList = brokerList;
	}

	public Producer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(Producer<String, String> producer) {
		this.producer = producer;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

}
