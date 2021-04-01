package willem.base.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author: willem
 * @create: 2021/04/01 16:29
 * @description:
 */
@Slf4j
public class CompletedFutureWorker implements Supplier<Long> {

    @Override
    public Long get() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int sleepTime = rand.nextInt(2000);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long resultLong = rand.nextLong(1000000);
        log.info("======{} sleep {} ms, return result:{}",Thread.currentThread().getName(), sleepTime,
                resultLong);
        return resultLong;
    }
}
