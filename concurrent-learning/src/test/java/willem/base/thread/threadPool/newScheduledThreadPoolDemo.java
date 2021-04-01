package willem.base.thread.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author weiyu
 * @description 创建一个定长线程池，支持定时及周期性任务执行
 * @create 2018/5/24 11:34
 * @since 1.0.0
 */
@Slf4j
public class newScheduledThreadPoolDemo {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    private CountDownLatch latch = new CountDownLatch(1);

    @Test
    public void test() throws InterruptedException {
        executor.scheduleAtFixedRate(new SimpleTask("simpleTask1"), 0, 5, TimeUnit.SECONDS);
        latch.await(10,TimeUnit.SECONDS);
    }
}
