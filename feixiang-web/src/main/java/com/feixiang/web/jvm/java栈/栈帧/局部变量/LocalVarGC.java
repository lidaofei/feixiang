package com.feixiang.web.jvm.java栈.栈帧.局部变量;

/**
 * 局部变量对垃圾回收的影响
 * 1.只要被局部变量表中直接或者间接引用的对象都是不会被回收
 * 2.System.gc()会触发两次gc，先minor gc，再Full GC
 */
public class LocalVarGC {
	public void localvarGc1(){
		//默认大对象5M就会直接进入老年代
		//full GC 会触发年轻代gc和老年代gc和永久代gc
		byte[] a=new byte[4*1024*1024];
		System.gc();
}
	public void localvarGc2(){
		byte[] a=new byte[4*1024*1024];
		//局部变量表应用清空了
		a=null;
		System.gc();
	}
	public void localvarGc3(){
		{
		byte[] a=new byte[4*1024*1024];
		}
		//a过了变量的作用域访问,a仍然占用着局部表的槽位
		System.gc();
	}
	public void localvarGc4(){
		{
		byte[] a=new byte[4*1024*1024];
		}
		//a过了变量的作用域访问,a在局部表的槽位被c复用
		int c=10;
		System.gc();
	}
	public void localvarGc5(){
		localvarGc1();
		System.gc();
	}
	public static void main(String[] args) {
		LocalVarGC ins=new LocalVarGC();
//		ins.localvarGc1();
//		ins.localvarGc2();
//		ins.localvarGc3();
//		ins.localvarGc4();
		ins.localvarGc5();
	}

			}
