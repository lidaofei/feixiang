package com.feixiang.nio.chatroom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 聊天室service端
 * @Author: lidaofei
 * @Date: 2019/4/4 17:47
 * @Description:
 */
public class ChatRoomService {
    public void service() throws Exception {
        ServerSocketChannel ssChannerl = ServerSocketChannel.open();
        ssChannerl.bind(new InetSocketAddress(9898));
        ssChannerl.configureBlocking(false);
        Selector selector = Selector.open();
        ssChannerl.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select()>0){
            Iterator<SelectionKey> keyIt = selector.selectedKeys().iterator();
            while (keyIt.hasNext()){
                SelectionKey key = keyIt.next();
                if(key.isAcceptable()){
                    SocketChannel sChannel = ssChannerl.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector,SelectionKey.OP_READ);
                } else if(key.isReadable()){
                    SocketChannel sChannel = (SocketChannel)key.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf))>0){
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
                keyIt.remove();
            }
        }

    }
}
