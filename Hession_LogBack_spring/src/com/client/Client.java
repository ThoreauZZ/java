package com.client;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.henry.HelloService;

public class Client {
	private static Logger logger = LoggerFactory.getLogger(Client.class);

	public static void main(String[] args) {
		String url = "http://localhost:8080/Hessionweb/hessian/hello";
		HessianProxyFactory factory = new HessianProxyFactory();
		HelloService hello;
		try {
			hello = (HelloService) factory.create(HelloService.class, url);
			System.out.println(hello.sayHello());
			logger.trace("======trace");
			logger.debug("======debug");
			logger.info("======info");
			logger.warn("======warn");
			logger.error("======error");
		} catch (MalformedURLException e) {
			logger.error(e.toString());
		}
	}
}
