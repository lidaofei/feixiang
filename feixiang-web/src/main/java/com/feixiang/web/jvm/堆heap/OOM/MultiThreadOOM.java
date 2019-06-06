package com.feixiang.web.jvm.堆heap.OOM;

/**
 *  线程栈溢出，过多线程导致OOM
 *  解决方法：
 * 1.减少最大堆空间
 * 2.减少一个线程所占内存空间
 */
public class MultiThreadOOM {
    public static class SleepThread implements Runnable{
        public void run(){
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]){
        for(int i=0;i<1500;i++){
            new Thread(new SleepThread(),"Thread"+i).start();
            System.out.println("Thread"+i+" created");
        }
    }
}
