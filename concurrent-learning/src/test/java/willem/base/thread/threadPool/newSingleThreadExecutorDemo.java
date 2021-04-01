package willem.base.thread.threadPool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu005
 * @Description 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 * @Date 2018/12/29 15:39
 */
public class newSingleThreadExecutorDemo {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {
        executor.submit(new LoopTask("loopTask1"));
        executor.submit(new LoopTask("loopTask2"));
        latch.await(3000, TimeUnit.MILLISECONDS);
        executor.shutdown();
    }
}
