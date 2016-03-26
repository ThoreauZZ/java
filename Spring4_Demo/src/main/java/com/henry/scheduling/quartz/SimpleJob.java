package com.henry.scheduling.quartz;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("[JOB] Welcome to Quartz!");
	}

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		// 调度工厂
		SchedulerFactory sf = new StdSchedulerFactory();

		// 从工厂中，获取一个任务调度实体
		Scheduler sched = sf.getScheduler();

		// 初始化任务实体
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
				.withIdentity("job1", "group1").build();

		// We create a SimpleTrigger that will fire off at the next round
		// minute:
		// compute a time that is on the next round minute
		Date runTime = DateBuilder.evenMinuteDate(new Date());
		// Trigger the job to run on the next round minute
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startAt(runTime)//执行的时间
				.withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))//每2秒
				.withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?"))//过两分钟的15秒处执行
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))//8点到17点2分钟执行一次
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?"))//1号和15号的10点执行一次
				.withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI"))//周一到周五30秒
				.withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN"))//周末30秒
				.build();

		// We now will associate the job to the trigger in the scheduler:
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
		sched.start();
//		Thread.sleep(90L * 1000L);
//		sched.shutdown(true);
	}

}
