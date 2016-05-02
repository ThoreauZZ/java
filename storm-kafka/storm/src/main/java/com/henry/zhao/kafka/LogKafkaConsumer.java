package com.henry.zhao.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class LogKafkaConsumer {

	private final ConsumerConnector consumer;
    private final String topic;
    private  ExecutorService executor;
 
    public LogKafkaConsumer(String a_zookeeper, String a_groupId, String a_topic) {
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper, a_groupId));
        this.topic = a_topic;
    }
 
    public void shutdown() {
        if (consumer != null) consumer.shutdown();
        if (executor != null) executor.shutdown();
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown, exiting uncleanly");
        }
   }
 
    public void run(int a_numThreads) {
    	Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(a_numThreads));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
        executor = Executors.newFixedThreadPool(a_numThreads);
        
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.execute(new ConsumerTest(stream, threadNumber));
            threadNumber++;
        }
    }
    
 
    private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", a_zookeeper);
        props.put("group.id", a_groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
 
        return new ConsumerConfig(props);
    }
 
    public static void main(String[] args) {
        String zooKeeper = "192.168.85.100:2181";
        String groupId = "foo";
        String topic = "foo";
        int threads = 3;
 
        LogKafkaConsumer example = new LogKafkaConsumer(zooKeeper, groupId, topic);
        example.run(threads);
 
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ie) {
 
        }
        example.shutdown();
    }
}
