package willem.base.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 16:14
 * @since 1.0.0
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncServerHandler> {

    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncServerHandler serverHandler) {
        //继续接受其他客户端请求
        AIOServer.clientCount++;
        System.out.println("======连接的客户端数："+AIOServer.clientCount);
        serverHandler.serverChannel.accept(serverHandler,this);
        //创建新的Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //异步读，第三个参数为接收消息回调的业务handler
        channel.read(buffer,buffer, new ServerReadHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncServerHandler serverHandler) {
        exc.printStackTrace();
        serverHandler.latch.countDown();
    }
}
