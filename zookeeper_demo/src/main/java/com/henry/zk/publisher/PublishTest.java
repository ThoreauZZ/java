/**     
 * @FileName: PublishTest.java   
 * @Package:com.zookeeperTest   
 * @Description: TODO  
 * @author: LUCKY    
 * @date:2016年1月24日 下午2:06:08   
 * @version V1.0     
 */
package com.henry.zk.publisher;

import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: PublishTest
 * @Description: 发布与订阅练习
 * @author: LUCKY
 * @date:2016年1月24日 下午2:06:08
 */
public class PublishTest {

	private static Logger logger = LoggerFactory.getLogger(PublishTest.class);
	static CuratorFramework client = null;
	static final String PATH = "/zhaozhou/conf/llyg.properties";
	static final String zkAddress = "192.168.217.100:2181";
	static final int timeout = 10000;
	static CountDownLatch countDownLatch = new CountDownLatch(1);
	// 客户端的监听配置

	static	ConnectionStateListener clientListener = new ConnectionStateListener() {

		public void stateChanged(CuratorFramework client,
				ConnectionState newState) {
			if (newState == ConnectionState.CONNECTED) {
				logger.info("connected established");
				countDownLatch.countDown();
			} else if (newState == ConnectionState.LOST) {
				logger.info("connection lost,waiting for reconection");
				try {
					logger.info("reinit---");
					reinit();
					logger.info("inited---");
				} catch (Exception e) {
					logger.error("re-inited failed");
				}
			}

		}
	};

	public static void main(String[] args) throws Exception {
		init();
		watcherPath(PATH, pathWatcher);
		Thread.sleep(Integer.MAX_VALUE);
	}

	public static void init() throws Exception {
		client = CuratorFrameworkFactory.builder().connectString(zkAddress)
				.sessionTimeoutMs(timeout)
				.retryPolicy(new RetryNTimes(5, 5000)).build();
		// 客户端注册监听，进行连接配置
		client.getConnectionStateListenable().addListener(clientListener);
		client.start();
		// 连接成功后，才进行下一步的操作
		countDownLatch.await();
	}

	public static void reinit() {
		try {
			unregister();
			init();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void unregister() {
		try {
			if (client != null) {
				client.close();
				client = null;
			}
		} catch (Exception e) {
			logger.info("unregister failed");
		}
	}

	// 对path进行监听配置
	public static String watcherPath(String path, CuratorWatcher watcher)throws Exception {
		byte[] buffer=client.getData().usingWatcher(watcher).forPath(path);
		System.out.println(new String(buffer));
		return new String(buffer);
	}

	public static String readPath(String path) throws Exception {
		byte[] buffer = client.getData().forPath(path);
		return new String(buffer);

	}

	static CuratorWatcher pathWatcher = new CuratorWatcher() {
		public void process(WatchedEvent event) throws Exception {
			// 当数据变化后，重新获取数据信息
			if (event.getType() == EventType.NodeDataChanged) {
				//获取更改后的数据，进行相应的业务处理
				String value=readPath(event.getPath());
				System.out.println(value);
			}

		}
	};
}
