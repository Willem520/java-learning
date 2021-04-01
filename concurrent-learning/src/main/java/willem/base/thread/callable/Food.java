package willem.base.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/27 17:29
 */
@Slf4j
public class Food implements Callable<Boolean> {

    public Boolean call() throws Exception {
        log.info("****** 购买食材：挑选 ******");
        Thread.sleep(5000);  // 模拟买菜时间
        log.info("****** 购买食材：购买完成 ******");
        return true;
    }
}