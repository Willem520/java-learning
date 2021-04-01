package willem.base.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author weiyu
 * @description 一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行，不可重用
 * @create 2018/5/21 10:23
 * @since 1.0.0
 */
@Slf4j
public class SimpleWorker implements Runnable {
    private String name;
    private long time;
    private CountDownLatch latch;

    public SimpleWorker(String name, long time, CountDownLatch latch) {
        this.name = name;
        this.time = time;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            log.info("****** {}执行耗时任务 ******", name);
            Thread.sleep(time);
            latch.countDown();
            log.info("****** {}执行完成,耗时{}ms", name, time);
        } catch (InterruptedException e) {
            log.error("****** 发生错误{} ******",e.getMessage());
        }
    }
}
