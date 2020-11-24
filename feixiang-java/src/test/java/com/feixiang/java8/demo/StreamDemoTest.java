package com.feixiang.java8.demo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author lidaofei
 * @date 2020/11/20 12:53
 */
public class StreamDemoTest {

    @Test
    public void test(){
        List<String> list = Stream.of("a", "b", "c").map(item -> item.toUpperCase()).collect(Collectors.toList());
        System.out.println(list);
        //true
        assertEquals(Arrays.asList("A", "B", "C"),list);
    }

    @Test
    public void test1(){
        List<TestUser> testUsers = Arrays.asList(
                new TestUser(1, "ldf1", 21),
                new TestUser(2, "ldf2", 22),
                new TestUser(3, "ldf3", 23)
        );

        TestUser testUMin = testUsers.stream().min(Comparator.comparing(testUser -> testUser.getAge())).get();
        System.out.println(testUMin);
        //true
        assertEquals(testUsers.get(0),testUMin);

        TestUser testUMax = testUsers.stream().max(Comparator.comparing(testUser -> testUser.getAge())).get();
        //true
        assertEquals(testUsers.get(2),testUMax);
    }

    @Test
    public void test2(){
        List<Integer> list = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)).flatMap(item -> item.stream()).collect(Collectors.toList());
        //true
        assertEquals(Arrays.asList(1,2,3,4),list);
    }

    @Test
    public void test3(){
        Integer sum = Stream.of(1, 2, 3).reduce((x, y) -> x + y).get();
        //true ,求和
        assertEquals(6,sum.intValue());
    }

    @Test
    public void test4(){
        Logger logger = new Logger();
        logger.debug(() -> "Look at this: ");
    }

    @Test
    public void test5(){
        HashSet<Integer> set = new HashSet<>(Arrays.asList(7, 3, 1,5,4));
        //不一定按照存入顺序输出，set存入就是无序的
        set.forEach(item -> System.out.println(item));
        System.out.println("===========");
        //按照存入的顺序，输出
        set.stream().forEachOrdered(item -> System.out.println(item));
        //输出：
//        1
//        3
//        4
//        5
//        7
//        ===========
//        1
//        3
//        4
//        5
//        7

        System.out.println("===========");
        List<Integer> list = Arrays.asList(7, 3, 1,5,4);
        list.forEach(item -> System.out.println(item));
        System.out.println("===========");
        list.stream().forEachOrdered(item -> System.out.println(item));
    }

    @Test
    public void test6(){
        List<Integer> list = Arrays.asList(5, 3, 6);
        //取出最大值
        Integer integer = list.stream().collect(Collectors.maxBy(Comparator.comparing(item -> item))).get();
        System.out.println(integer);

        //求平均值
        Double aDouble = list.stream().collect(Collectors.averagingInt(item -> item));
        System.out.println(aDouble);
    }

    @Test
    public void test7(){
        //分组
        List<TestUser> testUserList = Arrays.asList(new TestUser(1,"ldf1",1),new TestUser(1,"ldf10",10),new TestUser(2,"ldf2",2));
        Map<Integer, List<TestUser>> map = testUserList.stream().collect(Collectors.groupingBy(item -> item.getId()));
        System.out.println(map);
        //输出：
        //{1=[TestUser{id=1, name='ldf1', age=1}, TestUser{id=1, name='ldf10', age=10}], 2=[TestUser{id=2, name='ldf2', age=2}]}

        //分组取出，相同取出第一条
        Map<Integer, TestUser> collect = testUserList.stream().collect(Collectors.groupingBy(item -> item.getId(), Collectors.collectingAndThen(Collectors.toList(), value -> value.get(0))));
        System.out.println(collect);
        //输出
        //{1=TestUser{id=1, name='ldf1', age=1}, 2=TestUser{id=2, name='ldf2', age=2}}

        //分组取出，相同取出age最大的一条
        Map<Integer, TestUser> collect1 = testUserList.stream().collect(Collectors.groupingBy(item -> item.getId(), Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(value -> value.getAge())), va -> va.get())));
        System.out.println(collect1);
        //输出
        //{1=TestUser{id=1, name='ldf10', age=10}, 2=TestUser{id=2, name='ldf2', age=2}}

        //分组取出，相同取出age最大的一条，并且组合成集合
        List<TestUser> collect2 = testUserList.stream().collect(Collectors.groupingBy(item -> item.getId(), Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(value -> value.getAge())), va -> va.get()))).values().stream().collect(Collectors.toList());
        System.out.println(collect2);
        //输出
        //[TestUser{id=1, name='ldf10', age=10}, TestUser{id=2, name='ldf2', age=2}]

        //分组取出，相同取出name值
        Map<Integer, List<String>> collect3 = testUserList.stream().collect(Collectors.groupingBy(item -> item.getId(), Collectors.mapping(user -> user.getName(), Collectors.toList())));
        System.out.println(collect3);
        //输出
        //{1=[ldf1, ldf10], 2=[ldf2]}

        //取出name值拼接的字符串
        String collect4 = testUserList.stream().collect(Collectors.mapping(item -> item.getName(), Collectors.joining(",")));
        System.out.println(collect4);
        //输出
        //ldf1,ldf10,ldf2
    }

    @Test
    public void test8(){
        //打印直接结果
        List<Integer> collect = Stream.of(2, 5, 4, 8, 2).collect(Collectors.toList());
        System.out.println(collect);

        //需要知道lambda执行过程中的值，无法用forEach打印日志
//        List<Integer> collect2 = Stream.of(2, 5, 4, 8, 2).forEach(item -> System.out.println(item)).collect(Collectors.toList());
//        System.out.println(collect);
        //可以使用peek方法
        List<Integer> collect1 = Stream.of(2, 5, 4, 8, 2).peek(item -> System.out.println(item)).collect(Collectors.toList());
        System.out.println(collect1);

    }


}
