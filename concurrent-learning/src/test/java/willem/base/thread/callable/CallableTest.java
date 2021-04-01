package willem.base.thread.callable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/27 17:25
 */
@Slf4j
public class CallableTest {

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Boolean> kitchenWareFuture = executor.submit(new KitchenWare());
        Future<Boolean> foodFuture = executor.submit(new Food());
        while (true) {
            if (kitchenWareFuture.isDone() && foodFuture.isDone()) {
                log.info("****** 餐具及食材已准备就绪，可以开始做饭 ******");
                log.info("****** 烹制大餐中 ******");
                Thread.sleep(2000);
                log.info("****** 大餐制作完成，请享用 ******");
                executor.shutdown();
                break;
            }
            if (!kitchenWareFuture.isDone()) {
                log.info("****** 餐具还未到位 ******");
            }
            if (!foodFuture.isDone()) {
                log.info("****** 食材还未到 ******");
            }
        }
    }
}
