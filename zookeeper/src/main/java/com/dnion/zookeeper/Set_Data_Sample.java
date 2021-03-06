package com.dnion.zookeeper;

import java.util.Date;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class Set_Data_Sample {
	static String path = "/zk-book/c1";
	static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
			.sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3))
			.build();
	public static void main(String[] args) throws Exception {
		
		client.start();
		//创建一个节点
		client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path,"init".getBytes());
		Stat stat = new Stat();
		System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
		System.out.println(stat.getVersion());
		//client.delete().deletingChildrenIfNeeded().withVer`sion(stat.getVersion()).forPath(path);
	}

}
