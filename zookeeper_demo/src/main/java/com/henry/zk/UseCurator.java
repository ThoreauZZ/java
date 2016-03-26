package com.henry.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * <p> zk的第三方客户端 </p>
 * 
 * @author henry 2016-1-12 下午01:49:58
 */
public class UseCurator {
	private static CuratorFramework client;
	public void zkClient(String connnectHost) {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(connnectHost, retryPolicy);
		client.start();
	}
	public void zkClose(){
		client.close();
	}

//	public void distributedLock() {
//
//		InterProcessMutex lock = new InterProcessMutex(client, lockPath);
//		if (lock.acquire(maxWait, waitUnit)) {
//			try {
//				// do some work inside of the critical section here
//			} finally {
//				lock.release();
//			}
//		}
//	}
	
	public static void main(String[] args) throws Exception {
		UseCurator zk = new UseCurator();
		String connnectHost = "192.168.15.15:2181,192.168.15.15:2182,192.168.15.15:2183";
		zk.zkClient(connnectHost);
		client.create().forPath("/my/path");
		client.create().forPath("/my/path", "myData".getBytes());
		
		Thread.sleep(6000);
	}
}
