package com.henry.zk.curator.leader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;
/**
 * 
 * <p>zk领导者选择示例</p>
 * @author henry
 * 2016-1-12 下午02:45:21
 */
public class LeaderSelectorExample
{
    private static final int  CLIENT_NUM = 5;
    private static String connnectHost = "192.168.15.15:2181,192.168.15.15:2182,192.168.15.15:2183";
    private static final String PATH = "/examples/leader";

    public static void main(String[] args) throws Exception
    {

        System.out.println("--创建 " + CLIENT_NUM + " 个客户端, 客户端的领导权选举是公平的，每个都有机会！-----");

        List<CuratorFramework>  clients = Lists.newArrayList();
        List<ExampleClient>   examples = Lists.newArrayList();
        try
        {
            for ( int i = 0; i < CLIENT_NUM; ++i )
            {
                CuratorFramework client = CuratorFrameworkFactory.newClient(connnectHost, new ExponentialBackoffRetry(1000, 3));
                clients.add(client);

                ExampleClient example = new ExampleClient(client, PATH, "Client #" + i);
                examples.add(example);

                client.start();
                example.start();
            }

            System.out.println("---->>Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        finally
        {
            System.out.println("关闭！");

            for ( ExampleClient exampleClient : examples )
            {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for ( CuratorFramework client : clients )
            {
                CloseableUtils.closeQuietly(client);
            }
        }
    }
}