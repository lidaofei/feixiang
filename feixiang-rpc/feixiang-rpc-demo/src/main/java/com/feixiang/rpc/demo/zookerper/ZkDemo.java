package com.feixiang.rpc.demo.zookerper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookerpper api demo
 * @Author: lidaofei
 * @Date: 2019/8/12 19:27
 * @Description:
 */
public class ZkDemo {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        //连接zookerpper
        String connetStr = "47.112.119.242:2181";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(connetStr, 4000, new Watcher() {

            @Override
            public void process(WatchedEvent watchedEvent) {
                if(Event.KeeperState.SyncConnected == watchedEvent.getState()){
                    System.out.println("连接成功");
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();

        System.out.println(zooKeeper.getState());

        //zooKeeper.create("/ldf","0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 客户端查看创建成功

        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/ldf", null, stat);
        System.out.println(new String(data));

        zooKeeper.setData("/ldf","1".getBytes(),stat.getVersion());

    }

}
