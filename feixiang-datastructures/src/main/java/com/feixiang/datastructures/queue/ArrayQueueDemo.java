package com.feixiang.datastructures.queue;

import java.util.Scanner;

/**
 * 数组实现队列demo
 * @author lidaofei
 * @date 2020/4/12 16:59
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while(loop) {
            System.out.println("s:显示队列");
            System.out.println("a:添加数据到队列");
            System.out.println("g:从队列取出数据");
            System.out.println("h:查看队头数据");
            System.out.println("e:退出程序");
            //接受一个字符
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.printQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    try{
                        queue.addQueue(value);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try{
                        int val = queue.getQueue();
                        System.out.println(val);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try{
                        queue.printHeadQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("指令输入错误");
                    break;
            }
        }
    }
}


/**
 * 数组实现队列
 */
class ArrayQueue{
    //队列的最大容量
    private int maxSize;
    //队列头，指向头节点的前一个位置
    private int front;
    //队列尾，指向尾节点
    private int rear;
    //用于存放数据
    private int[] array;

    public ArrayQueue(int queueMaxSize) {
        this.maxSize = queueMaxSize;
        //初始值
        this.front = -1;
        //初始值
        this.rear = -1;
        //初始化数组
        this.array = new int[this.maxSize];
    }

    //分析
    //1.判读队列满：rear == maxSize - 1;
    //2.判读队列空：rear == front
    //3.添加数据：rear ++ ; array[rear] = number;
    //4.取出数据：front ++ ; array[front] ;

    /**
     * 1.判读队列满：rear == maxSize - 1;
     * @return
     */
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    /**
     * 2.判读队列空：rear == front
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 3.添加数据：rear ++ ; array[rear] = number;
     * @param number
     */
    public void addQueue(int number){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        rear ++ ;
        array[rear] = number;
    }

    /**
     * 4.取出数据：front ++ ; array[front] ;
     * @return
     */
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front ++;
        return array[front];
    }

    /**
     * 打印队列
     */
    public void printQueue(){
        for(int i = front+1; i<=rear; i++ ){
            System.out.printf("array[%d]=%d\n",i,array[i]);
        }
    }

    /**
     * 打印队列头
     */
    public void printHeadQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        System.out.println(array[front+1]);
    }
}
