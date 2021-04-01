package willem.base.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 16:07
 * @since 1.0.0
 */
public class AsyncServerHandler implements Runnable{
    public CountDownLatch latch;
    public AsynchronousServerSocketChannel serverChannel;

    public AsyncServerHandler(int port){
        try {
            //创建服务端通道
            serverChannel = AsynchronousServerSocketChannel.open();
            //绑定端口
            serverChannel.bind(new InetSocketAddress(port));
            System.out.println("======服务器已启动，端口号："+port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        serverChannel.accept(this,new AcceptHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
