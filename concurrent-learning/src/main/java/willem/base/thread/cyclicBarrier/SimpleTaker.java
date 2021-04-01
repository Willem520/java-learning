package willem.base.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author weiyu
 * @Description 一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行，可重用
 * @Date 2018/10/9 12:37
 */
public class SimpleTaker implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public SimpleTaker(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(String.format("****** %s开始执行部分操作 ******", Thread.currentThread().getName()));
            Thread.sleep(3000);
            System.out.println(String.format("****** %s部分操作完成 ******", Thread.currentThread().getName()));
            cyclicBarrier.await();

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("****** %s所有任务执行完成，开始下一步操作 ******", Thread.currentThread().getName()));
    }
}
