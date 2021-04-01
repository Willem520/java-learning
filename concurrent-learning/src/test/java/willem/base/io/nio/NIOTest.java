package willem.base.io.nio;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 15:28
 * @since 1.0.0
 */
public class NIOTest {
    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        NIOServer.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(1000);
        NIOClient.start();
        while (NIOClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
