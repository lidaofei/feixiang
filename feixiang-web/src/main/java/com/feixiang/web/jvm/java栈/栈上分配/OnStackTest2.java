package com.feixiang.web.jvm.java栈.栈上分配;

/**
 * 栈上分配，不是逃逸对象，对象分配在堆上
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB  -XX:+EliminateAllocations
 * 
 * @author Geym
 *
 */
public class OnStackTest2 {
    public static class User{
        public int id=0;
        public String name="";
    }
    private  User u= null;
    public  void alloc(){
        u=new User();
        u.id=5;
        u.name="geym";
    }
    public static void main(String[] args) throws InterruptedException {
        long b=System.currentTimeMillis();
        OnStackTest2 onStackTest = new OnStackTest2();
        for(int i=0;i<100000000;i++){
            onStackTest.alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}
