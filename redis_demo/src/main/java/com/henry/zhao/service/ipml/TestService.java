package com.henry.zhao.service.ipml;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.henry.zhao.domain.User;
import com.henry.zhao.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:spring-data-redis.xml" })
public class TestService {
	@Autowired
	private UserService userService;

	@Test
	public void test() {
		User user = new User();
		user.setId("id");
		user.setName("zhao");
		user.setPassword("123");
		userService.addUser(user);
	}

	@Test
	public void getUserTest() {
		User user = userService.getUser("id");
		System.out.println(user);
	}

	@Test
	public void addFile() throws IOException {
		File f = new File("src/main/java/com/henry/zhao/service/ipml/UserServiceImpl.java");
		FileInputStream is = new FileInputStream(f);
		int n = 2048;
		byte[] buffer = new byte[n];
		while ((is.read(buffer, 0, n) != -1) && (n > 0)) {
		}
		userService.addFile("file", buffer);

	}

	@Test
	public void getFile() throws IOException {
		byte[] bytes = userService.getFile("file");
		
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			file = new File("src/main/java/com/henry/zhao/service/ipml/file.java");
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String conf = "spring*.xml";
		// 实例化Spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		/*
		 * 有时候bean注入错误，找不到对应bean，可以使用该方法列出spring的所有bean。
		 */
		String[] strs = ac.getBeanDefinitionNames();
		for (String str : strs) {
			System.out.println(str);
		}
	}

}
