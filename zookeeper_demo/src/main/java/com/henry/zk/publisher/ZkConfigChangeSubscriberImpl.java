package com.henry.zk.publisher;

import java.util.List;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.util.StringUtils;

public class ZkConfigChangeSubscriberImpl implements ConfigChangeSubscriber {
	private ZkClient zkClient;
	private String rootNode;

	public ZkConfigChangeSubscriberImpl(ZkClient zkClient, String rootNode) {
		this.rootNode = rootNode;
		this.zkClient = zkClient;
	}
	

	@Override
	public void subscribe(String key, ConfigChangeListener listener) {
		String path = ZkUtils.getZkPath(this.rootNode, key);
		if (!this.zkClient.exists(path)) {
			throw new RuntimeException("配置("+ path+ ")不存在, "
					+ "必须先定义配置才能监听配置的变化, 请检查配置的key是否正确;"
					+ " 如果确认配置key正确, 那么需要保证先使用配置发布命令发布配置! ");
		}

		this.zkClient.subscribeDataChanges(path, new DataListenerAdapter(
				listener));
	}

	/**
	 * 触发ConfigChangeListener
	 * @param path
	 * @param value
	 * @param configListener
	 */
	private void fireConfigChanged(String path, String value,
			ConfigChangeListener configListener) {
		configListener.configChanged(getKey(path), value);
	}

	private String getKey(String path) {
		String key = path;

		if (!StringUtils.isEmpty(this.rootNode)) {
			key = path.replaceFirst(this.rootNode, "");
			if (key.startsWith("/")) {
				key = key.substring(1);
			}
		}

		return key;
	}

	public String getInitValue(String key) {
		String path = ZkUtils.getZkPath(this.rootNode, key);
		return (String) this.zkClient.readData(path);
	}

	public List<String> listKeys() {
		return this.zkClient.getChildren(this.rootNode);
	}
	/**
	 * 
	 * @描述：数据监听器适配类，当zk数据变化时，触发ConfigChangeListener
	 * @作者： henry.zhao
	 * @创建时间：2016年4月5日 下午7:57:33
	 * @版本：
	 */
	private class DataListenerAdapter implements IZkDataListener {
		private ConfigChangeListener configListener;
		public DataListenerAdapter(ConfigChangeListener configListener) {
			this.configListener = configListener;
		}
		public void handleDataChange(String s, Object obj) throws Exception {
			ZkConfigChangeSubscriberImpl.this.fireConfigChanged(s,
					(String) obj, this.configListener);
		}
		public void handleDataDeleted(String s) throws Exception {
		}
	}
}
