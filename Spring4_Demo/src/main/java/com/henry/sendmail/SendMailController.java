package com.henry.sendmail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

public class SendMailController {
	private static Logger logger = LoggerFactory.getLogger(SendMailController.class);
	/**
	 * 注意：VelocityEngine必须先这样定义，再注入
	 */
	private static VelocityEngine velocityEngine;

	
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/sendmail.xml");
		logger.info("初始化spring容器：{}",ac);
		
		JavaMailSenderImpl sender = ac.getBean("mailSender",JavaMailSenderImpl.class);
		/**
		 * 简单发送
		 */
//		SimpleMailMessage msg = ac.getBean("templateMessage",SimpleMailMessage.class);
//		msg.setSubject("java发送测试");//主题
//		msg.setText("---邮件内容---");
//		sender.send(msg);
		
		/**
		 * 复杂发送：附件（Attachments）
		 */
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo("-----@qq.com");
			helper.setFrom("---------@163.com");
			helper.setText("<html><body><h2>sdfadfas<h2></body></html>",true);
//			let's attach the infamous windows Sample file (this time copied to c:/)
			//附件
			FileSystemResource file = new FileSystemResource(new File("D:/猎豹截图.jpg"));
			helper.addAttachment("CoolImage.jpg", file);
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 使用模版引擎：freemarker，velocity
		 */
		try {
//			 VelocityEngineFactoryBean velovity = ac.getBean("velocityEngine",VelocityEngineFactoryBean.class);
//			 VelocityEngineFactory velocityEngineFactory = new VelocityEngineFactory();
//			 VelocityEngine createVelocityEngine = velocityEngineFactory.createVelocityEngine();
			 MimeMessage message2 = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message2, true);
			helper.setTo("-------@qq.com");
			helper.setFrom("----------@163.com");
			
			User user =  new User();
			user.setUserName("henry.赵");
			user.setEmailAddress("----------@163.com");
			Map model = new HashMap();
			model.put("user", user);
			
//			String text = VelocityEngineUtils.mergeTemplate(velovity, "htmlTep.vm", "UTF-8", model);
			String text = org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "com/henry/sendmail/htmlTep.vm","UTF-8",model);
			helper.setText(text,true);
			sender.send(message2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
