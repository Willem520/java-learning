package willem.base.thread.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author weiyu005
 * @Description 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * @Date 2018/12/29 15:20
 */
@Slf4j
public class newCachedThreadPoolDemo {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Test
    public void test(){
        for(int i=0; i<5; i++){
            executor.submit(new SimpleTask("task"+i));
        }
    }
}
