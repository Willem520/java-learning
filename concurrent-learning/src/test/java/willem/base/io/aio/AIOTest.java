package willem.base.io.aio;

import java.util.Scanner;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 16:59
 * @since 1.0.0
 */
public class AIOTest {

    public static void main(String[] args) throws InterruptedException {
        AIOServer.start();
        Thread.sleep(1000);
        AIOClient.start();
        System.out.println("======请输入请求信息：");
        while (AIOClient.sendMsg(new Scanner(System.in).nextLine()));
    }
}
