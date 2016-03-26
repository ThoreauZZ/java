package com.henry.zk;

import java.io.IOException;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
/**
 * <p> zk客户端</p>
 * @author henry
 * 2016-1-12 上午10:34:30
 */
public class Master implements Watcher {
	private ZooKeeper zk;
	private String connnectHost;
	// 标识自己
	private String serverId = Integer.toHexString(new Random().nextInt());
	// 是否是Leader
	private boolean isLeader;

	public Master(String connnectHost) {
		this.connnectHost = connnectHost;
	}
	
	/**
	 * zk 客户端将发起连接，显示：Session establishment complete on server
	 * 		sessionid = 0x25233a7e96a0000, negotiated timeout = 15000
	 * 
	 * 如果服务器关闭，将会不断请求连接。如果需要手动关闭连接，可以使用zk.close();
	 * @throws IOException
	 */
	public void startZK() throws IOException {
		zk = new ZooKeeper(connnectHost, 15000, this);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("---观察状态----"+event);
	}
	/**
	 * 
	 * @throws KeeperException
	 * @throws InterruptedException
	 */
	public void runForMaster() throws KeeperException, InterruptedException {
	    while (true) {
	        try {
	            zk.create("/master", serverId.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
	            // 创建/master节点成功
	            isLeader = true;
	            break;
	        } catch (KeeperException.NodeExistsException e) {
	            // /master已经存在
	            isLeader = false;
	            break;
	        } catch (KeeperException.ConnectionLossException e) {
	        }
	        if (checkMaster()) break;
	    }
	}
	/**
	* 检查master是否存在
	* @return 存在返回true，反之false
	*/
	private boolean checkMaster() throws InterruptedException{
	    while (true) {
	        try {
	            Stat stat = new Stat();
	            byte data[] = zk.getData("/master", false, stat);
	            isLeader = new String(data).equals(serverId);
	            return true;
	        } catch (KeeperException.NoNodeException e) {
	            // 没有master节点，可返回false
	            return false;
	        } catch (KeeperException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public static void main(String[] args) throws Exception {
		String connnectHost = "192.168.15.15:2181,192.168.15.15:2182,192.168.15.15:2183";
		Master m = new Master(connnectHost);
		m.startZK();
		m.runForMaster();
	    if (m.isLeader){
	        System.out.println("I am leader");
	    } else {
	        System.out.println("I am not leader");
	    }
		// wait for a bit
		Thread.sleep(60000);
	}
}
