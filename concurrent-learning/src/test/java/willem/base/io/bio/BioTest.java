package willem.base.io.bio;

import java.io.IOException;
import java.util.Random;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 14:07
 * @since 1.0.0
 */
public class BioTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {
            try {
                BIOServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(1000);

        //运行客户端
        char operators[] = {'+','-','*','/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(() -> {
            while(true){
                //随机产生算术表达式
                String expression = random.nextInt(10)+""+operators[random.nextInt(4)]+(random.nextInt(10)+1);
                BIOClient.send(expression);
                try {
                    Thread.currentThread().sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
