package willem.base.thread.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author weiyu005
 * @Description 创建一个固定数目的、可重用的线程池
 * @Date 2018/12/29 15:20
 */
@Slf4j
public class newFixedThreadPoolDemo {
    private ExecutorService executor = Executors.newFixedThreadPool(2);
    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {
        LoopTask task1 = new LoopTask("task");
        //LoopTask task2 = new LoopTask();

        Future future = executor.submit(task1);
        log.info("****** {} ******", future);
        Thread.sleep(1000);
        task1.stop();

        if (future.isDone()){
            log.info("****** 线程任务结束，关闭资源池 ******");
            executor.shutdown();
        }
    }

    @Test
    public void testMultiThread() throws InterruptedException {
        LoopTask loopTask1 = new LoopTask("loopTask1");
        LoopTask loopTask2 = new LoopTask("loopTask2");
        LoopTask loopTask3 = new LoopTask("loopTask3");
        executor.submit(loopTask1);
        executor.submit(loopTask2);
        executor.submit(loopTask3);
        latch.await(3, TimeUnit.SECONDS);
        loopTask1.stop();
        Thread.sleep(3000);
        executor.shutdown();
    }
}