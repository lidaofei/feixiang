package com.feixiang.web.jvm.堆heap.OOM;

import com.feixiang.web.jvm.方法区.动态代理.CglibBean;

import java.util.HashMap;

/**
 * 永久代内存溢出，jdk 1.6 时，永久代在堆上
 */
public class PermOOM {
    public static void main(String[] args) {
        try{
            for(int i=0;i<100000;i++){
                CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
            }
        }catch(Error e){
            e.printStackTrace();
        }
    }
}
