package com.feixiang.rpc.demo.zookerper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 *  Curator 是对zookeeper的封装
 * @Author: lidaofei
 * @Date: 2019/8/12 20:10
 * @Description:
 */
public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        String connetStr = "47.112.119.242:2181";

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connetStr).sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("ldf").build();

        curatorFramework.start();

        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/demo/node1","0".getBytes());

    }
}
