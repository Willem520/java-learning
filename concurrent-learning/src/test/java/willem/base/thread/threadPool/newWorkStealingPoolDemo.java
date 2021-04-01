package willem.base.thread.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu005
 * @Description 创建持有足够线程的线程池来支持给定的并行级别，并通过使用多个队列，减少竞争，
 * 需要穿一个并行级别的参数，如果不传，则被设定为默认的CPU数量
 * @Date 2018/12/29 15:53
 */
@Slf4j
public class newWorkStealingPoolDemo {
    private ExecutorService executor = Executors.newWorkStealingPool(4);

    @Test
    public void test() throws InterruptedException {
        //executor.submit(new RecursionTask(70));
        executor.awaitTermination(20, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
