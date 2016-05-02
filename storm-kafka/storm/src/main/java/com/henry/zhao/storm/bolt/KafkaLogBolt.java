package com.henry.zhao.storm.bolt;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.henry.zhao.storm.entity.LogBean;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import sun.org.mozilla.javascript.internal.json.JsonParser;

public class KafkaLogBolt implements IRichBolt {
	private OutputCollector collector;

	private static final long serialVersionUID = 8647789540941629452L;

	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	public void execute(Tuple input) {
		  String jsonStr = input.getString(0);
		  LogBean logBean = JSON.parseObject(jsonStr,LogBean.class);
		  System.out.println("=====^^^msg^^^^======="+logBean.getMessage());
	}

	public void cleanup() {
		// TODO Auto-generated method stub

	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub

	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
