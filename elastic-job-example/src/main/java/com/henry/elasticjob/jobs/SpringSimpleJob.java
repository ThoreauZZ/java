package com.henry.elasticjob.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 17/6/25 下午4:48.
 *
 * @author zhaozhou
 */

public class SpringSimpleJob implements SimpleJob{
    public void execute(ShardingContext shardingContext) {
        System.out.println("--------------job execute----------");
    }
}
