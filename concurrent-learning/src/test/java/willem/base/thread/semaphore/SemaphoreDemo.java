package willem.base.thread.semaphore;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author weiyu
 * @Description
 * @Date 2018/10/9 14:25
 */
@Slf4j
public class SemaphoreDemo {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void test(){
        //工人数
        int num = 8;
        //机器数目
        Semaphore semaphore = new Semaphore(5);
        for(int i=0; i<num; i++){
            new Thread(new SemaphoreWorker(i,semaphore)).start();
        }
        try {
            countDownLatch.await(15, TimeUnit.SECONDS);
            log.info("****** 主线程结束，程序退出 ******");
            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
