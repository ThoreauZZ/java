package com.henry.strategy;

public class Price {
	private MemberStrategy strategy;
	/**
	 * 构造函数：传入一个策略对象。
	 * @param strategy
	 */
	public Price(MemberStrategy strategy){
		this.strategy=strategy;
	}
	/**
	 * 计算图书价格
	 * @param bookPrice
	 * @return
	 */
	public double quote(double bookPrice){
		return this.strategy.calcPrice(bookPrice);
	}
}
