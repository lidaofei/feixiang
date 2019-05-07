package com.feixiang.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gc")
public class GcController {

    @RequestMapping("/testgc")
    public String testGc(){
        System.out.println("111");
        String str = "11";
        str.intern();
        return "hello";
    }

    private static List<byte[]>  list = new ArrayList<byte[]>();

    /**
     *
     */
    @RequestMapping("/testFullGc")
    public void testFullGc(){
        System.out.println("222");
        try{
            while (true){
                byte[] b = new byte[1024*1024];
                list.add(b);
                System.out.println(list.size());
            }
        }catch (Exception e){
            System.out.println(list.size());
            e.printStackTrace();
        }

    }




    /**
     * OutOfMemoryError：内存溢出
     * 意思是对象所占据的空间超过了堆内存的空间了,
     * 因此模拟它的方式就是组装一个超级大的对象就行，
     * 于是通过大量循环来生成字符串就可以达到这个效果
     */
    @RequestMapping("/testOutOfMemoryError")
    public void testOutOfMemoryError(){
        System.out.println("testOutOfMemoryError");
        String name = "hello";
        try{
            for (int i = 0; i < 10000000; i++) {
                name += name;
            }
            System.out.println(name);
        } catch (Exception e){
            System.out.println(name.length());
            e.printStackTrace();
        }
    }


    private static String base = "string";

    /**
     * 1.设置运行时jvm的参数：
     * Run->Run Configuartion->PermGenTest->Arguments->VM arguments
     * -XX:PermSize=5M -XX:MaxPermSize=5M -Xms5M -Xmx5M -XX:-UseGCOverheadLimit
     * 2.设置jdk为1.7，因为jdk1.8已经不支持XX:PermSize参数
     * 3.执行当前方法
     */
    @RequestMapping("/testOutOfMemoryError2")
    public void testOutOfMemoryError2(){
        System.out.println("testOutOfMemoryError2");
        try {
            List list = new ArrayList();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String str = base + base;
                base = str;
                list.add(str.intern());
            }
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * StackOverflowError ：栈溢出
     * 什么时候会让 Java Method Stack 栈溢出啊？
     * 栈的基本特点就是 FILO（First In Last Out），
     * 如果 in 的太多而 out 的太少，就好 overflow 了。
     * 而 Java Method Stack 的功能就是保存每一次函数调用时的“现场”，
     * 即为入栈，函数返回就对应着出栈，所以函数调用的深度越大，栈就变得越大，足够大的时候就会溢出。
     * 所以模拟 Java Method Stack 溢出，只要不断递归调用某一函数就可以。
     */
    @RequestMapping("/testStackOverflowError")
    public void testStackOverflowError(){
        System.out.println("testStackOverflowError");
        try {
            stackOverflow();
        } catch (Throwable e) {
            System.out.println("stack length: " + stackLength);
            e.printStackTrace();
        }
    }

    private static int stackLength = 0;
    private void stackOverflow(){
        ++ stackLength;
        stackOverflow();
    }



}
