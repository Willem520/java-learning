package willem.base.thread.condition;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author weiyu
 * @description
 * @create 2018/5/21 11:15
 * @since 1.0.0
 */
@Slf4j
public class ConditionDemo {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        MyQueue queue = new MyQueue<Integer>(10);

        new Thread(() -> {
            while (true) {
                Double value = Math.abs(Math.random() * 20);
                queue.add(value.intValue());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                log.info("****** 取出{} ******", queue.remove());
            }
        }).start();

        latch.await(1, TimeUnit.MINUTES);
    }
}
