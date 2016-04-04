package com.henry.strategy;

public interface MemberStrategy {
	/**
	 * 
	 * @param 图书价格
	 * @return 折扣后价格
	 */
	public double calcPrice(double bookPrice);
}
