package com.henry.strategy;

public class PrimaryMemberStrategy implements MemberStrategy {
	
	public double calcPrice(double bookPrice) {
		System.out.println("触及会员没有折扣");
		return bookPrice;
	}

}
