package com.henry.zhao.log.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

public interface Formatter {
	String format(ILoggingEvent event);
}
