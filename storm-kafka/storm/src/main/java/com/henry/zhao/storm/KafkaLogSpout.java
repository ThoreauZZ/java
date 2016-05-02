package com.henry.zhao.storm;

import java.util.Map;
import java.util.Properties;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;

public class KafkaLogSpout extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2967079891650832440L;

	public void nextTuple() {
		// TODO Auto-generated method stub

	}

	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {

	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub

	}

}
