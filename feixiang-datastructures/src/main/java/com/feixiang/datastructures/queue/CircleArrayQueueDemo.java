package com.feixiang.datastructures.queue;

import org.omg.CORBA.MARSHAL;

import java.util.Scanner;

/**
 * 数组实现循环队列
 * @author lidaofei
 * @date 2020/4/19 11:46
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
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
 * 数组实现循环队列
 */
class CircleArrayQueue{
    //队列最大的容量
    private int maxSize;

    //队列头，指向队列的头节点
    private int front;

    //队列尾，指向队列的尾节点的后一个节点
    private int rear;

    //数组
    private int[] array;

    CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = 0;
        array = new int[maxSize];
    }

    //1.队列空：rear == front
    //2.队列满：(rear+1)%maxSize == front
    //3.队列含有多少元素：（rear+maxSize-front）% maxSize = n
    //4.添加数据：array[rear] = number; rear++;
    //5.出队数据：number = array[front]; front++;

    /**
     *队列满：(rear+1)%maxSize == front
     * @return
     */
    public boolean ifFull(){
        return (rear + 1)%maxSize == front;
    }

    /**
     * 队列空：rear == front
     * @return
     */
    public boolean ifEmpty(){
        return rear == front;
    }

    /**
     * 添加数据：array[rear] = number; rear++;
     * @param number
     */
    public void addQueue(int number){
        //队满判断
        if(ifFull()){
            throw new RuntimeException("循环队列已满");
        }
        //入队
        array[rear] = number;

        /*
        方法一：判断位置再重置
        rear++;

        //下标循环
        if(rear==maxSize){
            rear = 0;
        }
         */
        //方法二：rear + 1 之后再取模，自然会重置下标
        rear = (rear + 1)%maxSize;
    }

    /**
     * 出队数据：number = array[front]; front++;
     * @return
     */
    public int getQueue(){
        if(ifEmpty()){
            throw new RuntimeException("循环队列已空");
        }
        int reuslt = array[front];
        /*
        方法一：
        front ++;
        if(front == maxSize){
            front = 0;
        }
        */
        //方法二：
        front = (front +1)%maxSize;
        return reuslt;
    }

    /**
     * 打印队列
     */
    public void printQueue(){
        //队列中有效的数据
        int leng = (rear + maxSize - front) % maxSize;
        int index = front;
        for(int i=0; i<leng ; i++){
            System.out.printf("array[%d]=%d\n",index,array[index]);
            /*
            方法一：
            index++;
            //下标重置
            if(index == maxSize){
                index = 0;
            }
             */
            //方法二：
            index = (index + 1) % maxSize;
        }
    }

    /**
     * 打印头节点
     */
    public void printHeadQueue(){
        System.out.printf("array[%d]=%d\n",front,array[front]);
    }

}
