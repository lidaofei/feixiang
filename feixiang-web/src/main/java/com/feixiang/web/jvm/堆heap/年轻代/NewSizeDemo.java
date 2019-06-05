
package com.feixiang.web.jvm.堆heap.年轻代;

/**
 * -Xmx20m -Xms20m -Xmn1m   -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 * 1.注：当年轻代设为1m时，会提示：
 * Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
 * 系统默认最小的为1536k，他会用系统默认的1536k，而我们设置的1024k不会生效
 * 2.-XX:SurvivorRatio=2 ，即：两个s区：eden区=2:4，一个s区：1/6
 *
 * -Xmx20m -Xms20m -Xmn7m   -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 * -Xmx20m -Xms20m -Xmn15m  -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 
 * -Xmx20M -Xms20M -XX:NewRatio=2  -XX:+PrintGCDetails
 * 
 * @author Geym
 */
public class NewSizeDemo {
    public static void main(String[] args) {
        //每次创建都会覆盖上一次b的引用，所以每次gc都会回收掉上几次产生的对象数据
       //byte[] b=null;
       for(int i=0;i<100;i++) {
           byte[] b=new byte[1*1024*1024];
       }
    }
}
