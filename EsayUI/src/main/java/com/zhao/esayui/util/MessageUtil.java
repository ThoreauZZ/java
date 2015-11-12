package com.zhao.esayui.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;

public class MessageUtil {
	/**
	 * 按照UUID算法生成一个字符串
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		String uid =  uuid.toString();
		return uid.replace("-", "");
	}
	
	/**
	 * 将传入的消息进行md5加密
	 * 不可逆
	 * 任意长度变等长
	 * @param message
	 * @return
	 */
	public static String md5(String message){
		try {
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
			byte[] input = message.getBytes();
			byte[] output = md.digest(input);//将字节信息加密
			//利用base64将加密后的字节信息转成字符串
			String str = base64Encode(output);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 利用Base64算法将字节信息转成字符串
	 * @param input
	 * @return
	 * jdk6.0自带的API
	 */
	public static String base64Encode1(byte[] input){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(input);
	}
	//采用commons-codec-1.9.jar
	public static String base64Encode(byte[] input){
		 return Base64.encodeBase64String(input);
	}
	
	public static String base64Decode(String msg){
		try{
			byte[] output = Base64.decodeBase64(msg);
			return new String(output,"UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
//		System.out.println(md5("ddddddddddddddd"));
//		System.out.println(md5("a"));
//		System.out.println(getUUID());
//		System.out.println(getUUID());
	}
	
	
	
}
