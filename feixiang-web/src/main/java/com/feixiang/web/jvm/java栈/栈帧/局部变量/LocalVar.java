package com.feixiang.web.jvm.java栈.栈帧.局部变量;

/**
 *局部变量表，槽位复用
 */
public class LocalVar {

	public static void localvar1(){
		int a=0;
		System.out.println(a);
		//b不复用a的槽位
		int b=0;
	}
	public static void localvar2(){
		{
		int a=0;
		System.out.println(a);
		}
		//b复用a的槽位
		int b=0;
	}

	public static void main(String[] args) {
        localvar1();
		localvar2();
	}

}
