package willem.base.thread.cyclicBarrier;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author weiyu
 * @Description
 * @Date 2018/10/9 12:42
 */
public class CyclicBarrierDemo {

    @Test
    public void test() throws InterruptedException {
        int parties = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);
        for (int i=0; i<parties; i++){
            new Thread(new SimpleTaker(cyclicBarrier)).start();
        }
        Thread.sleep(10000);
    }
}
