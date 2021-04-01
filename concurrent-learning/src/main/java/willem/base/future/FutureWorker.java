package willem.base.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: willem
 * @create: 2021/04/01 16:27
 * @description:
 */
public class FutureWorker implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int sleepTime = rand.nextInt(2000);
        Thread.sleep(sleepTime);
        Long resultLong = rand.nextLong(1000000);
        System.out.println(String.format("======%s sleep %s ms, return result:%s",Thread.currentThread().getName(), sleepTime,
                resultLong));
        return resultLong;
    }
}
