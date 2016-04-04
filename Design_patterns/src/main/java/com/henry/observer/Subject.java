package com.henry.observer;
/**
 * 
 * @描述：观察者模式=发布者+订阅者。
 * 	<p> 主题接口</p>
 * @作者： henry.zhao
 * @创建时间：2016年4月4日 下午3:02:24
 * @版本：
 */
public interface Subject {
	/**
	 * 注册观察者
	 * @param observer
	 */
	public void registerObserver(Observer observer);
	/**
	 * 移除观察者
	 * @param observer
	 */
	public void removeObserver(Observer observer);
	/**
	 * 唤醒所有观察者
	 */
	public void notifyAllObservers();
}
