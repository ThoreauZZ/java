package com.henry.zhao.springrabbit.getQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.henry.zhao.springrabbit.listen.myQueueListen;
import com.henry.zhao.springrabbit.send.Student;

public class ObtainQueueData {
	private static Logger LOGGER = LoggerFactory.getLogger(ObtainQueueData.class);

	public static void main(String[] args) {
		// 1:获取注入bean
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		LOGGER.info("初始化容器：" + ac.toString());
		AmqpTemplate amqpTemplate = ac.getBean("amqpTemplate",
				AmqpTemplate.class);
		Student s = new Student();
	}
}
