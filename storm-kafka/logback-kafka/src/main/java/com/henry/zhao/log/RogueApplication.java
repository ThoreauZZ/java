package com.henry.zhao.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RogueApplication {
	private static final Logger log = LoggerFactory.getLogger(RogueApplication.class);

	public static void main(String[] args) throws InterruptedException {
		int slowCount = 6;
		int fastCount = 15;
		for (int i = 0; i < slowCount; i++) {
			log.warn("this is a warning (slow state).");
			Thread.sleep(5000);
		}
		for (int i = 0; i < fastCount; i++) {
			log.warn("this is a warning (fast state).");
			Thread.sleep(1000);
		}
		for (int i = 0; i < slowCount; i++) {
			log.warn("this is a warning (slow state).");
			Thread.sleep(5000);
		}

	}
}
