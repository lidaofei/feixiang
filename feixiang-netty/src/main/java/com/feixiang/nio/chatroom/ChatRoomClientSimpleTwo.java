package com.feixiang.nio.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 聊天室客户端，简易版
 * 可以收到服务的消息
 * @Author: lidaofei
 * @Date: 2019/4/22 9:24
 * @Description:
 */
public class ChatRoomClientSimpleTwo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private SocketChannel sChannel = null;

    private ByteBuffer buffer = null;

    private  Selector selector = null;


    public static void main(String[] orgs){
        try {
            new ChatRoomClientSimpleTwo().client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //客服端
    public void client() throws Exception {
        //1.获取通道
        sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2.切换到非阻塞模式
        sChannel.configureBlocking(false);

        //3.新建缓存区，并指定大小
        buffer = ByteBuffer.allocate(1024);

        selector = Selector.open();
        sChannel.register(selector,SelectionKey.OP_READ);

        new write().start();

        new read().start();

        //5.循环写入缓存区，再把缓存写入通道
//        while (scanner.hasNext()){
//            String next = scanner.next();
//            buffer.put((sdf.format(new Date())+" "+next).getBytes());
//            buffer.flip();
//            sChannel.write(buffer);
//            buffer.clear();
//        }

        //6.读取服务器回传数据
//        while (selector.select()>0){
//            Iterator<SelectionKey> selIts = selector.selectedKeys().iterator();
//            while(selIts.hasNext()){
//                SelectionKey key = selIts.next();
//                if(key.isReadable()){
//                    SocketChannel sChannelK = (SocketChannel)key.channel();
//                    ByteBuffer bf = ByteBuffer.allocate(1024);
//                    int len = 0;
//                    while ((len = sChannelK.read(bf))>0){
//                        System.out.println(new String(bf.array(),0,len));
//                    }
//                }
//                selIts.remove();
//            }
//        }
    }

    //异步写
    private class write extends Thread{
        @Override
        public void run() {
            //4.获取键盘输入的字符
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                try {
                    String next = scanner.next();
                    buffer.put((sdf.format(new Date())+" "+next).getBytes());
                    buffer.flip();
                    sChannel.write(buffer);
                    buffer.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //异步读
    private class read extends Thread {
        @Override
        public void run() {
            try {
                while (selector.select()>0){
                    Iterator<SelectionKey> selIts = selector.selectedKeys().iterator();
                    while(selIts.hasNext()){
                        SelectionKey key = selIts.next();
                        if(key.isReadable()){
                            SocketChannel sChannelK = (SocketChannel)key.channel();
                            ByteBuffer bf = ByteBuffer.allocate(1024);
                            int len = 0;
                            while ((len = sChannelK.read(bf))>0){
                                bf.flip();
                                System.out.println(new String(bf.array(),0,len));
                                bf.clear();
                            }
                        }
                        selIts.remove();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
