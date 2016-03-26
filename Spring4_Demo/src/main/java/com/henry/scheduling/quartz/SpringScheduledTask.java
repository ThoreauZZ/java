package com.henry.scheduling.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringScheduledTask {
	private static final Logger LOG = LoggerFactory.getLogger(SpringScheduledTask.class);
    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("定时器开始执行");
        }
    }
}
