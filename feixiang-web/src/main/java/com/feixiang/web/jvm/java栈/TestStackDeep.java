package com.feixiang.web.jvm.java栈;

/**
 * 栈溢出
 * 1.设置单个栈大小 -Xss1m
 * @author Administrator
 *
 */
public class TestStackDeep {
	private static int count=0;
	public static void recursion(long a,long b,long c){
		long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
		long e1=1,f1=2,g1=3,h1=4,i1=5,k1=6,q1=7,x1=8,y1=9,z1=10;
		count++;
		recursion(a,b,c);
	}
	public static void recursion(){
		count++;
		recursion();
	}
	public static void main(String args[]){
		try{
			//栈溢出，调用层级更少，含有局部变量
			recursion(0L,0L,0L);

			//栈溢出，调用层级更多，不含局部变量
			//recursion();
		}catch(Throwable e){
			System.out.println("deep of calling = "+count);
			e.printStackTrace();
		}
	}
}
