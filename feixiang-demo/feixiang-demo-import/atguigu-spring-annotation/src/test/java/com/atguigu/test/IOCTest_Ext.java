package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.ext.ExtConfig;

public class IOCTest_Ext {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext(ExtConfig.class);
		
		
		//�����¼���
		applicationContext.publishEvent(new ApplicationEvent(new String("�ҷ�����ʱ��")) {
		});
		
		applicationContext.close();
	}

}
