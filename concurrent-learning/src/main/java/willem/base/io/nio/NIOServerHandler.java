package willem.base.io.nio;

import willem.base.io.util.Calculator;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 14:34
 * @since 1.0.0
 */
public class NIOServerHandler implements Runnable{
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean started;

    public NIOServerHandler(int port){
        try{
            //创建选择器
            selector = Selector.open();
            //打开监听通道
            serverChannel = ServerSocketChannel.open();
            //如果为true，则此通道将被置于阻塞模式
            serverChannel.configureBlocking(false);
            //绑定端口
            serverChannel.socket().bind(new InetSocketAddress(port),1024);
            //监听客户端连接请求
            serverChannel.register(selector,SelectionKey.OP_ACCEPT);
            //标记服务器已开启
            started = true;
            System.out.println("======服务器已启动，端口号："+port);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        started = false;
    }

    @Override
    public void run() {
        while (started){
            try {
                //无论是否有读写事件发生，selector每隔1s被唤醒一次
                selector.select(1000);
                //阻塞,只有当至少一个注册的事件发生的时候才会继续.
                //selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (ScriptException e) {
                        if (key != null){
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException, ScriptException {
        if (key.isValid()){
            //处理新接入的请求消息
            if (key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //通过serverSocketChannel的accept创建SocketChannel实例,完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
                SocketChannel sc = ssc.accept();
                //设置为非阻塞
                sc.configureBlocking(false);
                //注册为读
                sc.register(selector,SelectionKey.OP_READ);
            }
            //读消息
            if (key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                //创建byteBuffer,并开辟1M的缓冲区
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                //读取请求码流，返回读取到的字节数
                int readBytes = sc.read(buffer);
                //读取到字节，对字节进行编解码
                if (readBytes > 0){
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    buffer.flip();
                    //根据缓冲区可读字节数创建字节数组
                    byte[] bytes = new byte[buffer.remaining()];
                    //将缓冲区可读字节数组复制到新建的数组中
                    buffer.get(bytes);
                    String expression = new String(bytes,"UTF-8");
                    System.out.println("======服务器接收到消息："+expression);
                    //处理数据
                    String result = Calculator.cal(expression).toString();
                    //发送应答请求
                    doWrite(sc,result);
                }else{
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel,String response) throws IOException {
        //将消息编码为自己数组
        byte[] bytes = response.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区数据
        channel.write(writeBuffer);
    }
}
