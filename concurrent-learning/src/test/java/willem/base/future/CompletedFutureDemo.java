package willem.base.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author weiyu005@ke.com
 * @Description
 * @Date 2019/9/12 16:01
 */
@Slf4j
public class CompletedFutureDemo {

    @Test
    public void test(){
        long total = 0L;
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<CompletableFuture<Long>> completableFutureList = new ArrayList<>();
        for (int i=0; i< 12; i++){
            completableFutureList.add(CompletableFuture.supplyAsync(new CompletedFutureWorker(), pool));
        }
        total = completableFutureList.stream().map(CompletableFuture::join).reduce((value1, value2)-> value1+value2).get();
        log.info("======total:{}", total);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> pool.shutdown()));
    }
}
