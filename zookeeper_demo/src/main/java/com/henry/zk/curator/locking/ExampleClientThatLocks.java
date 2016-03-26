package com.henry.zk.curator.locking;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import java.util.concurrent.TimeUnit;
/**
 * <p>分布式锁运用</p>
 * @author henry
 * 2016-1-12 下午05:25:28
 */
public class ExampleClientThatLocks
{
    private final InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;
    
    //构造函数初始化
    public ExampleClientThatLocks(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName)
    {
        this.resource = resource;
        this.clientName = clientName;
        lock = new InterProcessMutex(client, lockPath);
    }
    
    /**
     * doWork方法：具体用到锁
     * @param time
     * @param unit
     * @throws Exception
     */
    public void doWork(long time, TimeUnit unit) throws Exception
    {
        if ( !lock.acquire(time, unit) )
        {
            throw new IllegalStateException(clientName + " could not acquire the lock");
        }
        try
        {
            System.out.println(clientName + " has the lock");
            System.out.println("--不重复id---"+resource.getId());
        }
        finally
        {
            System.out.println(clientName + " releasing the lock");
            lock.release(); // always release the lock in a finally block
        }
    }
}
