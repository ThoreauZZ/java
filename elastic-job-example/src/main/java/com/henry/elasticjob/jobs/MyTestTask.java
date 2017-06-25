package com.henry.elasticjob.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * 17/6/25 下午7:04.
 *
 * @author zhaozhou
 */
public class MyTestTask implements SimpleJob{
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("------task");
    }
}
