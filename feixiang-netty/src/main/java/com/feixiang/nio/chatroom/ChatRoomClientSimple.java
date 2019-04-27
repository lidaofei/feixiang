package com.feixiang.nio.chatroom;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 聊天室客户端，简易版
 * @Author: lidaofei
 * @Date: 2019/4/22 9:24
 * @Description:
 */
public class ChatRoomClientSimple {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] orgs){
        try {
            new ChatRoomClientSimple().client();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //客服端
    public void client() throws Exception {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2.切换到非阻塞模式
        sChannel.configureBlocking(false);

        //3.新建缓存区，并指定大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Selector selector = Selector.open();
        sChannel.register(selector, SelectionKey.OP_READ);

        //4.获取键盘输入的字符
        Scanner scanner = new Scanner(System.in);

        //5.循环写入缓存区，再把缓存写入通道
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put((sdf.format(new Date()) + " " + next).getBytes());
            buffer.flip();
            sChannel.write(buffer);
            buffer.clear();
        }
    }

}
