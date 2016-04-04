package com.henry.observer;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @描述：主题对象：管理某些数据，一旦数据改变，新的数据将送到观察者手中；
 * @作者： henry.zhao
 * @创建时间：2016年4月4日 下午3:05:59
 * @版本：
 */
public class HeadCounter implements Subject {
	
	/**
	 * 观察者对象：如果需要订阅，必须注册。
	 */
	private List<Observer> observerList;
	private List<String> jobList;

	public HeadCounter() {
		observerList = new ArrayList<Observer>();
		jobList = new ArrayList<String>();
	}

	public void registerObserver(final Observer observer) {
		if (!observerList.contains(observer)) {
			observerList.add(observer);
		}
	}

	public void removeObserver(Observer observer) {
		if (observerList.contains(observer)) {
			observerList.remove(observer);
		}

	}

	public void notifyAllObservers() {
		for (Observer observer : observerList) {
			observer.update(this);
		}
	}

	public void addJob(final String job) {
		this.jobList.add(job);
		notifyAllObservers();
	}

	public List<String> getJobs() {
		return jobList;
	}

	public String toString() {
		return jobList.toString();
	}

}
