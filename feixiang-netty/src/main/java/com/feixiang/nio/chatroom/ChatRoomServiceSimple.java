package com.feixiang.nio.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 聊天室服务器端，简易版
 * @Author: lidaofei
 * @Date: 2019/4/22 9:24
 * @Description:
 */
public class ChatRoomServiceSimple {


    public static void main(String[] orgs){
        try {
            new ChatRoomServiceSimple().service();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //服务端
    public void service() throws Exception {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2.切换到非阻塞模式
        ssChannel.configureBlocking(false);

        //3.绑定链接
        ssChannel.bind(new InetSocketAddress(9898));

        //4.获取选择器
        Selector selector = Selector.open();

        //5.将通道注册到选择器上，并且指定“监听事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询的获取监听器上已经“准备就绪”的事件
        while (selector.select()>0){
            //7.获取当前选择器上的所有注册的“选择键（已就绪的监听事件）”
            Iterator<SelectionKey> selectKeyIt = selector.selectedKeys().iterator();
            //8.循环是选择键
            while (selectKeyIt.hasNext()){
                //9.获取单个选择键
                SelectionKey next = selectKeyIt.next();
                //10.如果是“接收就绪”
                if(next.isAcceptable()){
                    SocketChannel clientChannel = ssChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    //11.如果是“读就绪”
                    SocketChannel clientChannel = (SocketChannel)next.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = clientChannel.read(buf))>0){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                    //11.1 接收到数据，给客户端提示一个消息
                    buf.put("服务端回传，已收到".getBytes());
                    buf.flip();
                    clientChannel.write(buf);
                    buf.clear();
                }
            }

            //12.取消选择键
            selectKeyIt.remove();
        }

       
       
       


    }
}
