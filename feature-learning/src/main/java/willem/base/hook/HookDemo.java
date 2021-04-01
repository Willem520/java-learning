package willem.base.hook;

import lombok.extern.slf4j.Slf4j;

/**
 * @author weiyu
 * @description 钩子方法简单示例
 * @create 2018/8/9 14:06
 * @since 1.0.0
 */
@Slf4j
public class HookDemo {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("this is a shutdown hook");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        System.out.println("======模拟程序执行");
        Thread.sleep(3000);
        System.out.println("hello hook");
    }
}
