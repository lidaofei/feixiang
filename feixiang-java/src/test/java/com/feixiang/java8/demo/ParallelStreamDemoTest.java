package com.feixiang.java8.demo;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lidaofei
 * @date 2020/11/21 11:43
 */
public class ParallelStreamDemoTest {

    @Test
    public void test1(){
        //并行付初始值
        int[] ints = new int[100];
        Arrays.parallelSetAll(ints,i -> i);
        System.out.println(ints[0]+","+ints[1]);

        //1.parallel 并行化执行
        int sum = Arrays.stream(ints).parallel().sum();
        System.out.println(sum);
        //输出
        //0,1
        //4950
    }
}
