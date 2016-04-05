package com.henry.zk.publisher;

import java.util.List;
/**
 * 
 * @描述：配置改变的订阅者，在每一個zk文件上订阅一個监听器
 * @作者： henry.zhao
 * @创建时间：2016年4月5日 下午7:53:48
 * @版本：
 */
public interface ConfigChangeSubscriber {
	public abstract String getInitValue(String paramString);

	public abstract void subscribe(String paramString, ConfigChangeListener paramConfigChangeListener);

	public abstract List<String> listKeys();
}
