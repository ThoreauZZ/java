package com.henry.zhao.springrabbit.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendMessage {
	// 日志记录
	private static Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);

	@Autowired
	// 注解在spring容器没开启前不能用
	private AmqpTemplate amqpTemplate;
	@Value("${rabbit.queue.myqueue}")
	private String myQueue;

	@RequestMapping("/go.form")
	public String toLogin() {
		LOGGER.info(">>>>>转向添加页面");
		return "addmq";// 发出go.form请求进入addmq.jsp
	}

	@RequestMapping("/addmq.form")
	public void addMq(Student s) {// 如果参数和属性相同，自动同步。
		if (amqpTemplate == null || myQueue == null) {
			LOGGER.debug("amqpTemplate或者queue注入----失败");
		} else {
			LOGGER.info("amqpTemplate或者queue注入-----成功");
		}
		if (s == null) {
			LOGGER.debug("添加mq参数出现错误");
		} else {
			LOGGER.debug("实体匹配------正确");
		}
		amqpTemplate.convertAndSend(myQueue, s);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("把实体放入mq中。", s.toString());
		}
	}

	// =====未部署tomcat前测试
	public static void main(String[] args) {
		Student s = new Student();
		s.setAge(22);
		s.setName("Henry.zhao");
		// 1:获取注入bean
		String conf = "applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		LOGGER.info("初始化容器："+ac.toString());
		AmqpTemplate amqpTemplate = ac.getBean("amqpTemplate",AmqpTemplate.class);
		// 2:发送mq
		amqpTemplate.convertAndSend("myQueue", s);
		LOGGER.debug(">>>myQueue>>>:"+s.toString());
		System.exit(0);
	}
}
