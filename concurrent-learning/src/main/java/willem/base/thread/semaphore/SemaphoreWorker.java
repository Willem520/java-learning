package willem.base.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author weiyu
 * @Description 一般用于控制对某组资源的访问权限
 * @Date 2018/10/9 14:26
 */
public class SemaphoreWorker implements Runnable{
    private int id;
    private Semaphore semaphore;

    public SemaphoreWorker(int id, Semaphore semaphore){
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(String.format("****** 工人%s占用一个机器在生产 ******", this.id));
            Thread.sleep(3000);
            semaphore.release();
            System.out.println(String.format("****** 工人%s释放出机器，结束工作 ******", this.id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
