package com.henry.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {

	public double calcPrice(double bookPrice) {
		System.out.println("高级会员，折扣为20%");
		return bookPrice * 0.8;
	}

}
