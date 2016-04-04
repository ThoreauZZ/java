package com.henry.strategy;

public class IntermediateMemberStrategy implements MemberStrategy {

	public double calcPrice(double bookPrice) {
		System.out.println("中级会员，10%折扣");
		return bookPrice * 0.9;
	}

}
