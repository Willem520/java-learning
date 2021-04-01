package willem.base.thread.countDownLatch;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author weiyu
 * @description
 * @create 2018/5/21 10:30
 * @since 1.0.0
 */
@Slf4j
public class CountDownLatchDemo {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(new SimpleWorker("worker1",(long)(Math.random()*6000),latch)).start();
        new Thread(new SimpleWorker("worker2",(long)(Math.random()*2000),latch)).start();
        latch.await();
        log.info("****** 前置任务完成，开始执行 ******");
        new Thread(new SimpleWorker("worker3",(long)(Math.random()*3000),latch)).start();
    }
}
