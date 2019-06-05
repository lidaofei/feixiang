package com.feixiang.web.jvm.堆heap.软引用;

import java.lang.ref.SoftReference;

/**
 * -Xmx10m
 * After gc, soft ref is exists
 * after create byte array ,soft ref is gc
 * 
 * @author geym
 *
 */
public class SoftRef {
    public static class User{
        public User(int id,String name,byte[] bs){
            this.id=id;
            this.name=name;
            this.bs=bs;
        }
        public int id;
        public String name;
        private byte[] bs;
        
        @Override
        public String toString(){
            return "[id="+String.valueOf(id)+",name="+name+"]";
        }
    }
    public static void main(String[] args) {
        User u=new User(1,"geym",new byte[5*1024*1024]);
        SoftReference<User> userSoftRef = new SoftReference<User>(u);
        u=null;
        
        System.out.println("==============="+userSoftRef.get());
        System.gc();
        System.out.println("After GC:");
        System.out.println("==============="+userSoftRef.get());

        //内存不足时，清楚软引用
        byte[] b=new byte[1024*1024*9];
        System.gc();
        System.out.println("==============="+userSoftRef.get());
    }
}
