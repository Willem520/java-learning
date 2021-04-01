package willem.base.thread.threadPool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu005
 * @Description 创建一个单例线程池，定期或延时执行任务
 * @Date 2018/12/29 15:45
 */
public class newSingleThreadScheduledExecutorDemo {
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {
        executor.scheduleAtFixedRate(new SimpleTask("simpleTask1"),0, 5, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(new SimpleTask("simpleTask2"),0, 5, TimeUnit.SECONDS);
        latch.await();
    }
}
