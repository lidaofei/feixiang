package com.feixiang.web.jvm.方法区.动态代理;

import java.util.HashMap;

/**
 * JDK1.6 1.7 -XX:+PrintGCDetails -XX:PermSize=5M -XX:MaxPermSize=5m
 * 
 * JDK1.8 -XX:+PrintGCDetails -XX:MaxMetaspaceSize=5M
 * 
 * @author Geym
 *
 */
public class PermTest {
	public static void main(String[] args) {
		int i = 0;
		try {
			for (i = 0; i < 100000; i++) {
				CglibBean bean = new CglibBean("com.feixiang.web.jvm.方法区.动态代理" + i, new HashMap());
			}
		} catch (Exception e) {
			System.out.println("total create count:" + i);
		}
	}
}
