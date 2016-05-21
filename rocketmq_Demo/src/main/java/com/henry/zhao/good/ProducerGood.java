package com.henry.zhao.good;

@Slf4j
public class ProducerGood {
	private String nameAddr;

	private String topic;

	private String groupName;

	private DefaultMQProducer producer;

	/**
	 * @Description 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例。
	 * @author erdaoya
	 * @date 2016年4月27日 下午4:46:18
	 */
	public void start() {
		initMqConfig();
		log.info("MQProducer start ! address: {},  topic: {}, groupName: {}", nameAddr, topic, groupName);
		producer = new DefaultMQProducer(groupName);
		producer.setNamesrvAddr(setNamesrvAddr);
		try {
			producer.start();
			log.debug("MQProducer start success!");
		} catch (MQClientException e) {
			log.error("ICMQProducer start fail ! {}", e.getMessage());
		}
	}

	/**
	 * @Description 发送MQ
	 * @author erdaoya
	 * @date 2016年4月27日 下午4:51:09
	 * @param json
	 */
	public void send(String json) {
		Message message = new Message(topic, json.getBytes());
		SendResult sendResult;
		try {
			sendResult = producer.send(message);
			if (sendResult.getSendStatus() == sendResult.getSendStatus().SEND_OK) {
				log.debug("sendMqToIc send mq message success! groupName is {}", groupName);
			} else {
				log.error("sendMqToIc mq推送失败，msgId:{},sendStatus:{}", sendResult.getMsgId(),
						sendResult.getSendStatus());
			}
		} catch (Exception e) {
			log.error("sendMqToIc mq推送发生异常，{}", e.getMessage());
		}
	}

	/**
	 * @Description 从ecp_configs表初始化配置信息。
	 * @author jiale
	 * @date 2016年4月27日 下午4:47:07
	 */
	private void initMqConfig() {
		this.nameAddr = configCenter.get("user.search.mq.address");
		this.topic = configCenter.get("user.register.mq.topic");
		this.groupName = configCenter.get("user.register.mq.group");
	}
	/**
	 * @Description 清理资源
	 * @author erdaoya
	 * @date 2016年4月27日 下午4:50:36
	 */
	@PreDestroy
	public void shutdown() {
		producer.shutdown();
	}
}
