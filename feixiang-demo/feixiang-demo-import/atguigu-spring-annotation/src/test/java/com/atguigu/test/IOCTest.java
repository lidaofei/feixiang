package com.atguigu.test;

import java.util.Map;

import com.atguigu.config.MainConfig1_0;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.atguigu.bean.Blue;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;

public class IOCTest {
	@Test
	public void testImport(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		printBeans(applicationContext);
		Blue bean = applicationContext.getBean(Blue.class);
		System.out.println(bean);
		
		//����Bean��ȡ���ǵ���getObject�����Ķ���
		Object bean2 = applicationContext.getBean("colorFactoryBean");
		Object bean3 = applicationContext.getBean("colorFactoryBean");
		System.out.println("bean�����ͣ�"+bean2.getClass());
		System.out.println(bean2 == bean3);
		
		Object bean4 = applicationContext.getBean("&colorFactoryBean");
		System.out.println(bean4.getClass());
	}
	
	private void printBeans(AnnotationConfigApplicationContext applicationContext){
		String[] definitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
	}
	
	@Test
	public void test03(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		//��̬��ȡ����������ֵ��Windows 10
		String property = environment.getProperty("os.name");
		System.out.println(property);
		for (String name : namesForType) {
			System.out.println(name);
		}
		
		Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
		System.out.println(persons);
		
	}
	
	@Test
	public void test02(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
//		String[] definitionNames = applicationContext.getBeanDefinitionNames();
//		for (String name : definitionNames) {
//			System.out.println(name);
//		}
//		
		System.out.println("ioc�����������....");
		Object bean = applicationContext.getBean("person");
		Object bean2 = applicationContext.getBean("person");
		System.out.println(bean == bean2);
	}

	/**
	 * ��ȡ������bean����
	 * ���� componentScans����
	 */
	@Test
	public void testComponentScans01(){
		//ע��ķ�ʽ�������е�bean
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		//���������е�bean
		String[]  definitionNames= applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
	}

	/**
	 * ��ȡ����������bean����
	 */
	@Test
	public void test01(){
		//ע��ķ�ʽ�������е�bean
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig1_0.class);
		//���������е�bean
		String[]  definitionNames= applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
	}
}