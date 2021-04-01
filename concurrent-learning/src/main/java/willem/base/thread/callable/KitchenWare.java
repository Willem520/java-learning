package willem.base.thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/27 17:28
 */
@Slf4j
public class KitchenWare implements Callable<Boolean> {

    public Boolean call() throws Exception {
        log.info("****** 购买餐具：下单 ******");
        log.info("****** 购买餐具：等待送货 ******");
        Thread.sleep(5000);  // 模拟送货时间
        log.info("****** 购买餐具：快递送到 ******");
        return true;
    }
}
