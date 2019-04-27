package com.feixiang.nio.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 *  聊天室client端
 * @Author: lidaofei
 * @Date: 2019/4/4 17:47
 * @Description:
 */
public class ChatRoomClient {

    private SocketChannel socketChannel = null;
    private Selector selector = null;

    public ChatRoomClient() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1",9898);
        socketChannel = SocketChannel.open(socketAddress);
        socketChannel.configureBlocking(false);

        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void star(){
        //新建一个线程读取数据
        //新建一个线程写数据
    }

    /**
     * 新建一个写的线程
     */
    private class Writer extends Thread{
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                try {
                    String line = scanner.nextLine();
                    Charset charset = Charset.forName("utf-8");
                    socketChannel.write(charset.encode(line));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 新建一个写的线程
     */
    private class Read extends Thread {
        @Override
        public void run() {
            while (true){
                try {
                    int readyChannels = selector.select();
                    if(readyChannels == 0){
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for(SelectionKey selectionKey : selectionKeys){
                                            
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
