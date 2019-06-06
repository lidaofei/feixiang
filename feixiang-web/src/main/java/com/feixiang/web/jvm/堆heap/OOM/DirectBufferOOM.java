package com.feixiang.web.jvm.堆heap.OOM;

import java.nio.ByteBuffer;

/**
 * 直接内存溢出
 */
public class DirectBufferOOM {
    public static void main(String args[]){
        for(int i=0;i<1024;i++){
            ByteBuffer.allocateDirect(1024*1024);
            System.out.println(i);
//            System.gc();
        }
    }
}
