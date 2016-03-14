package com.dnion.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class Create_Session_Sample_Fluent {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
		CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
				.sessionTimeoutMs(5000)
				.retryPolicy(retryPolicy)
				.build();
		client.start();
		client.create().withMode(CreateMode.EPHEMERAL).forPath("/zk_test","test".getBytes());
		Thread.sleep(Integer.MAX_VALUE);
	}

}
