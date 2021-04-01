package willem.base.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author weiyu005@ke.com
 * @Description
 * @Date 2019/9/12 15:02
 */
@Slf4j
public class FutureDemo {
    volatile static Long total = new Long(0);

    @Test
    public void test() throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Callable<Long>> tasks = new ArrayList<>();
        for (int i=0; i< 5; i++){
            tasks.add(new FutureWorker());
        }
        List<Future<Long>> futures = pool.invokeAll(tasks);
        for (Future<Long> future: futures){
            total += future.get();
        }
        log.info("======total:{}", total);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> pool.shutdown()));
    }
}