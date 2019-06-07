package com.feixiang.web.jvm.监控工具.线程相互死锁;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lidaofei
 * @Date: 2019/6/6 17:41
 * @Description:
 */

@RestController
@RequestMapping("/util")
public class DeadLockController {

    final static Logger logger = Logger.getLogger(DeadLockController.class);
    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();

    @RequestMapping("/lock")
    public void lock() throws InterruptedException {
        logger.info("DeadLockController.lock() start");
        DeadLock car2south = new DeadLock(south);
        DeadLock car2north = new DeadLock(north);
        car2south.start();
        car2north.start();
        Thread.sleep(1000);
        logger.info("DeadLockController.lock() end");
    }

    @RequestMapping("/threadNum")
    public void threadNum(){
        logger.info("DeadLockController.threadNum() start");
        List<User> userList = new ArrayList<User>();
        for(int i=0;i<10000;i++){
            userList.add(new User(Long.parseLong(i+""),"user"+i));
        }
        logger.info("DeadLockController.threadNum() end");
    }
}
