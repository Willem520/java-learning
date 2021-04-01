package willem.base.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weiyu
 * @description lambda表达式
 * @create 2018/7/31 16:50
 * @since jdk1.8
 */
public class LambdaDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaDemo.class);

    public static void main(String[] args) {
        //无lambda表达式时
        new Thread(new Runnable() {

            @Override
            public void run() {
                LOGGER.info("****** java without lambda ******");
            }
        }).start();

        new Thread(() ->{
            int count = 0;
            do {
                LOGGER.info("****** java lambda{} ******", count++);
            }while (count < 10);
        }).start();
    }
}
