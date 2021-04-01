package willem.base.thread;

/**
 * @author weiyu
 * @description
 * @create 2018/6/14 11:25
 * @since 1.0.0
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread previousThread = new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("****** 开始执行费时操作 ******");
                for (int i=0;i<10;i++){
                    System.out.println(String.format("****** 第%s次循环执行 ******", i));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        previousThread.start();
        //添加join后，只有当previousThread操作执行完成，主线程才会继续执行
        previousThread.join();
        System.out.println("****** 这里是主线程 ******");
    }
}
