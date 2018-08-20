package willem.weiyu.hook;

/**
 * @author weiyu
 * @description 钩子方法简单示例
 * @create 2018/8/9 14:06
 * @since 1.0.0
 */
public class HookDemo {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("this is a shutdown hook")));
        System.out.println("======模拟程序执行");
        Thread.sleep(5000);
        System.out.println("hello hook");
    }
}
