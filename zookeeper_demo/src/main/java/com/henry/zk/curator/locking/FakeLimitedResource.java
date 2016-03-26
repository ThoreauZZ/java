package com.henry.zk.curator.locking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>同一时间只能有一个进程处理 </p>
 * @author henry
 * 2016-1-12 下午05:37:43
 */
public class FakeLimitedResource {
	/*
	 * AtomicBoolean用法：
	 * 	方法：compareAndSet(boolean expect, boolean update)
	 * 		 1. 比较AtomicBoolean和expect的值，如果一致，执行方法内的语句
	 * 		 2. 把AtomicBoolean的值设成update
	 * 两件事是一气呵成的，这两个个动作之间不会被打断，任何内部或者外部的语句都不可能在两个动作之间运行。为多线程的控制提供了解决的方案
	 */
	private final AtomicBoolean inUse = new AtomicBoolean(false);
	
	@SuppressWarnings("finally")
	public String getId() throws InterruptedException {
		// in a real application this would be accessing/manipulating a shared resource
		if (!inUse.compareAndSet(false, true)) {
			/*
			 * 第一个进程进来，inUse为false，和compareAndSet(false, true)中false相同；
			 * 所以：inUse=true，返回true；！true ，不进入if的方法体；
			 * 执行Thread.sleep();这时候，其他进程进来后inUse=ture，抛出异常！
			 */
			throw new IllegalStateException("Needs to be used by one client at a time");
		}
		String id="";
		try {
			Thread.sleep((long) (3 * Math.random()));
			
			File file = new File(
					"F:/Workspace_new/zk_demo/src/main/resources/id.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			id = br.readLine().trim();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(Long.parseLong(id) + 1 + "");
			bw.flush();
			bw.close();
			br.close();
			
		}catch (IOException e) {
			return null;
		}
		finally {
			/*
			 * 出方法后，把inUse设置为false，让其他进程进来；
			 */
			inUse.set(false);
			return id;
		}
	}
}