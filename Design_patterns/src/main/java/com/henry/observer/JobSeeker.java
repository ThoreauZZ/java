package com.henry.observer;
/**
 * 
 * @描述：观察者对象，如果需要订阅，必须得注册到主题
 * @作者： henry.zhao
 * @创建时间：2016年4月4日 下午3:09:57
 * @版本：
 */
public class JobSeeker implements Observer {
	private String name;

	public JobSeeker(String name) {
		this.name = name;
	}

	public void update(Subject subject) {
		System.out.println(this.name + "收到推送信息");
		System.out.println(subject);

	}

}
