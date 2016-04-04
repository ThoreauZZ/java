package com.henry.strategy;
/**
 * @描述：测试策略模式
 * @作者： henry.zhao
 * @创建时间：2016年4月4日 下午1:49:55
 * @版本：
 */
public class StrategyClient {
	public static void main(String[] args) {
		MemberStrategy strategy = new AdvancedMemberStrategy();
		Price price = new Price(strategy);
		double quote = price.quote(100.00);
		System.out.println("图书价格："+quote);
	}
}
