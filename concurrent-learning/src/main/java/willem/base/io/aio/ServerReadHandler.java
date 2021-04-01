package willem.base.io.aio;

import willem.base.io.util.Calculator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author weiyu@gomefinance.com.cn
 * @description
 * @create 2018/6/5 16:20
 * @since 1.0.0
 */
public class ServerReadHandler implements CompletionHandler<Integer,ByteBuffer> {
    private AsynchronousSocketChannel channel;

    public ServerReadHandler(AsynchronousSocketChannel channel){
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] message = new byte[attachment.remaining()];
        attachment.get(message);
        try {
            String expression = new String(message, "UTF-8");
            System.out.println("======服务器收到消息: " + expression);
            String calrResult  = Calculator.cal(expression).toString();
            //向客户端发送消息
            doWrite(calrResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String result){
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                //如果没有发送完，就继续发送直到完成
                if (buffer.hasRemaining())
                    channel.write(buffer,buffer,this);
                else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //异步读
                    channel.read(readBuffer,readBuffer,new ServerReadHandler(channel));
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
