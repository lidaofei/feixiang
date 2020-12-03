package com.feixiang.java8.demo;

import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * @author lidaofei
 * @date 2020/11/20 11:53
 */
public class LambdaDemoTest {

    @Test
    public void test1(){

        System.out.println(11);

        Runnable runnable = () -> System.out.println("hello ldf");

        ActionListener actionListener = actionEvent -> System.out.println("hello ldf2");

        Runnable mutiRunnable = () -> {
            System.out.println("hello 3");
            System.out.println("ldf 3");
        };

        BinaryOperator<Long> add1 = (x,y) -> {return x + y;};

        BinaryOperator<Long> add2 = (x,y) -> x + y;

        BinaryOperator<Long> add3 = (Long x,Long y) -> x + y;

        System.out.println(22);

        //执行方法，这些方法不会被执行，只是一个匿名类的定义，不会输出值
        //输出如下：
//        11
//        22
    }

    @Test
    public void test2(){
        new Thread(() -> System.out.println("hello ldf1")).start();
        //方法被执行了
        //打印如下；
//        hello ldf1
    }
}
