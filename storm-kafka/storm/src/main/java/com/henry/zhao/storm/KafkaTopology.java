package com.henry.zhao.storm;

import java.util.UUID;

import com.henry.zhao.storm.bolt.KafkaLogBolt;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import storm.kafka.ZkHosts;

public class KafkaTopology {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("kafkaspout", createKafkaSpout(),1);
		builder.setBolt("kafkalogbolt", new KafkaLogBolt()).shuffleGrouping("kafkaspout");
		Config conf = new Config();
		conf.setDebug(false);
		conf.setMaxTaskParallelism(1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
	}

	private static IRichSpout createKafkaSpout() {
		String zkConnString = "192.168.85.100:2181";
		String topicName = "foo";
		BrokerHosts hosts = new ZkHosts(zkConnString);
		SpoutConfig spoutConfig = new SpoutConfig(hosts, topicName, "/" + topicName, UUID.randomUUID().toString());
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		return kafkaSpout;
	}

}
