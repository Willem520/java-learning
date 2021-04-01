package willem.base.thread.threadPool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/29 17:47
 */
public class ForkJoinPoolDemo {
   private ForkJoinPool pool = new ForkJoinPool(4);

   @Test
    public void test() throws InterruptedException {
       pool.submit(new RecursionTask(123));
       pool.awaitTermination(10, TimeUnit.SECONDS);
   }
}
