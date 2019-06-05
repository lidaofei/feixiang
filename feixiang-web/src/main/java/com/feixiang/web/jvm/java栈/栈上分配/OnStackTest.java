package com.feixiang.web.jvm.java栈.栈上分配;

/**
 * 栈上分配
 * 1.好处是函数结束后自行销毁，不用垃圾回收介入，提高系统性能
 * 2.基础是逃逸分析，判断对象的作用域是否可能逃逸出函数体
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB  -XX:+EliminateAllocations
 * 
 * @author Geym
 *
 */
public class OnStackTest {
    public static class User{
        public int id=0;
        public String name="";
    }
    public void alloc(){
        User u=new User();
        u.id=5;
        u.name="geym";
    }
    public static void main(String[] args) throws InterruptedException {
        long b=System.currentTimeMillis();
        OnStackTest onStackTest = new OnStackTest();
        for(int i=0;i<100000000;i++){
            onStackTest.alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}
