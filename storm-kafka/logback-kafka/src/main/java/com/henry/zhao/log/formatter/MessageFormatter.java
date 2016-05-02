package com.henry.zhao.log.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class MessageFormatter implements Formatter {

	public String format(ILoggingEvent event) {
		return event.getFormattedMessage();
	}

}
