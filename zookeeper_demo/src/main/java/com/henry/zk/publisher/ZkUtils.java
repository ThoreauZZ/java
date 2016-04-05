package com.henry.zk.publisher;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 
 * @描述：zk工具类
 * @作者： henry.zhao
 * @创建时间：2016年4月5日 下午7:44:41
 * @版本：
 */
public class ZkUtils {
	private static final Logger logger = LoggerFactory.getLogger(ZkUtils.class);
	
	/**
	 * 获取节点
	 * @param rootNode
	 * @param key
	 * @return
	 */
	public static String getZkPath(String rootNode, String key) {
		if (!StringUtils.isEmpty(rootNode)) {
			if (key.startsWith("/")) {
				key = key.substring(1);
			}
			if (rootNode.endsWith("/")) {
				return rootNode + key;
			}
			return rootNode + "/" + key;
		}

		return key;
	}
	
	/**
	 * 创建节点
	 * @param client
	 * @param path
	 */
	public static void mkPaths(ZkClient client, String path) {
		String[] subs = path.split("\\/");
		if (subs.length < 2) {
			return;
		}
		String curPath = "";
		for (int i = 1; i < subs.length; i++) {
			curPath = curPath + "/" + subs[i];
			if (!client.exists(curPath)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Trying to create zk node: " + curPath);
				}
				client.createPersistent(curPath);
				if (logger.isDebugEnabled())
					logger.debug("Zk node created successfully: " + curPath);
			}
		}
	}

	public static String formatAsMonthDate(Date requestTime) {
		return new SimpleDateFormat("MMdd").format(requestTime);
	}

	public static class StringSerializer implements ZkSerializer {
		private String encoding;

		public StringSerializer(String encoding) {
			this.encoding = encoding;
		}

		public Object deserialize(byte[] abyte0) throws ZkMarshallingError {
			try {
				return new String(abyte0, this.encoding);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}

		}

		public byte[] serialize(Object obj) throws ZkMarshallingError {
			if (obj == null) {
				return null;
			}

			if (!(obj instanceof String)) {
				throw new ZkMarshallingError(
						"The input obj must be an instance of String.");
			}
			try {
				return ((String) obj).getBytes(this.encoding);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
