package willem.base.tool;

import org.junit.jupiter.api.Test;
import java.time.temporal.ChronoUnit;

/**
 * @author weiyu
 * @description
 * @create 2018/5/25 14:45
 * @since 1.0.0
 */
public class DateUtilDemo {

    @Test
    public void test(){
        System.out.println(DateUtil.getTomorrow());
        System.out.println(DateUtil.getDate("2017-10-30",2,ChronoUnit.DAYS));
        System.out.println(DateUtil.getDateTime("2017-10-01 12:13:14",-2,ChronoUnit.DAYS));
        System.out.println(DateUtil.getCurrentDateTime());
    }
}
