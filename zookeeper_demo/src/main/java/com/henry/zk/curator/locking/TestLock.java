package com.henry.zk.curator.locking;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>curator分布式锁学习</p>
 * 也可以自己通过zk实现分布式锁：
 *          1、客户端调用create()方法创建名为“_locknode_/guid-lock-”的节点，需要注意的是，这里节点的创建类型需要设置为EPHEMERAL_SEQUENTIAL。
 *          2、客户端调用getChildren(“_locknode_”)方法来获取所有已经创建的子节点，注意，这里不注册任何Watcher。
 *          3、客户端获取到所有子节点path之后，如果发现自己在步骤1中创建的节点序号最小，那么就认为这个客户端获得了锁。
 *          4、如果在步骤3中发现自己并非所有子节点中最小的，说明自己还没有获取到锁。此时客户端需要找到比自己小的那个节点，
 *              然后对其调用exist()方法，同时注册事件监听。
 *          5、之后当这个被关注的节点被移除了，客户端会收到相应的通知。这个时候客户端需要再次调用getChildren(“_locknode_”)方法来获取所有已经创建的子节点，
 *              确保自己确实是最小的节点了，然后进入步骤3
 * Created by henry.zhao on 2016/3/12.
 */
public class TestLock {
    private final InterProcessMutex lock;
    private static int count=1;
    private static final String PATH;

    static {
        PATH = "/examples/locks";
    }

    //构造函数初始化
    public TestLock(CuratorFramework client, String lockPath)
    {
        lock = new InterProcessMutex(client, lockPath);
    }
    public static void main(String[] args) throws Exception{
        final ExecutorService service = Executors.newFixedThreadPool(15);//创建线程数固定5个的线程池
        final TestingServer  zkServer=new TestingServer();//使用curator测试端模拟zookeeper
        try {
            for(int i=0;i<10;i++) {
                /*
                 * Callable:
                 *      java创建线程的2种方式，一种是直接继承Thread，另外一种就是实现Runnable接口。
                 *      这2种方式都有一个缺陷就是：在执行完任务之后无法获取执行结果。
                 *
                 *  Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果
                 *         1.Callable则一般都是提交给ExecuteService来执行。
                 *         2.Callable实现的是 V call()方法
                 *
                 */
                Callable<Void> callable = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework  client = CuratorFrameworkFactory.newClient(zkServer.getConnectString(),new ExponentialBackoffRetry(1000, 3));
                        try {
                            client.start();
                            TestLock testLock = new TestLock(client,PATH);
                            testLock.doWork();
                        }finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(callable);
            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
            System.out.println("---子线程执行完后，再执行此代码！---");
        }catch (Exception e){
            e.printStackTrace();
        } finally
        {
            System.out.println("---如果zkServer不为空，关闭！---");
            CloseableUtils.closeQuietly(zkServer);
        }
    }

    public void doWork()throws Exception{
        if ( !lock.acquire(3000, TimeUnit.SECONDS) )
        {
            throw new IllegalStateException(Thread.currentThread() + " could not acquire the lock");
        }
        try {
            System.out.println("--不重复id---"+count++);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.release();
        }
    }
}
