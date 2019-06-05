package com.feixiang.web.jvm.堆heap;

import java.util.Vector;

/**
 * -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
 * 注：打印在当前模块的主目录上
 *
 * -Xmx20m -Xms5m  "-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p"  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
 * 
 * printstack.bat:
 * D:/tools/jdk1.7_40/bin/jstack -F %1 > D:/a.txt
 * @author Geym
 *
 */
public class DumpOOM {
    public static void main(String[] args) {
        Vector v=new Vector();
        for(int i=0;i<25;i++) {
            v.add(new byte[1*1024*1024]);
        }
    }
}
