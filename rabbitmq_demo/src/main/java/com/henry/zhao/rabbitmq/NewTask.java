package com.henry.zhao.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private static final String TASK_QUEUE_NAME = "task_queue";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("192.168.217.100");
		factory.setUsername("mq");
		factory.setPassword("mq");
		factory.setPort(5672);
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    /*
	     * When RabbitMQ quits or crashes it will forget the queues and messages unless you tell it not to. 
	     * Two things are required to make sure that messages aren't lost: we need to mark both the queue and 
	     * messages as durable.
	     */
	    boolean durable = true;//持久化队列
	    channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);

	    String message = getMessage(argv);
	    //MessageProperties.PERSISTENT_TEXT_PLAIN 持久化消息
	    channel.basicPublish("", TASK_QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + message + "'");

	    channel.close();
	    connection.close();
	  }

	  private static String getMessage(String[] strings) {
	    if (strings.length < 1)
	      return "Hello World!";
	    return joinStrings(strings, " ");
	  }

	  private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	      words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	  }
}
