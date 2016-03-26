package com.henry.zhao.springrabbit.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.henry.zhao.springrabbit.send.Student;

/**
 * 
 * @author henry.zhao
 *
 */
public class myQueueListen {
	private static Logger LOGGER = LoggerFactory.getLogger(myQueueListen.class);
	public void handleMessage(Student s) {
		LOGGER.info("监听数据");
		LOGGER.info("取出数据："+s.toString());
	}
}
