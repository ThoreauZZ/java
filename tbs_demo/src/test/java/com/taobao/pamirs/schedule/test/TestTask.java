package com.taobao.pamirs.schedule.test;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;

public class TestTask implements IScheduleTaskDealSingle{
	protected static transient Logger log = LoggerFactory.getLogger(TestTask.class);

	@Override
	public List selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List taskItemList, int eachFetchDataNum)
			throws Exception {
		Date time = new Date();
		
		log.error(">>>>>>>>>>"+time+"--selectTasks处理任务---========================-------");
		return null;
	}

	@Override
	public Comparator getComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(Object task, String ownSign) throws Exception {
		Thread.sleep(50);
		log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>处理任务>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return true;
	}

}
