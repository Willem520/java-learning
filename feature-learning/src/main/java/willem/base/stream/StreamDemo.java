package willem.base.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author weiyu
 * @description stream操作
 * @create 2018/7/31 16:56
 * @since jdk1.8
 */
public class StreamDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamDemo.class);

    public static void main(String[] args) {
        //1.通过Stream静态方法创建
        Stream<Integer> stream = Stream.of(1,3,5,8);

        //2.通过Collection子类获取Stream
        List<Integer> list = new ArrayList();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            list.add(rand.nextInt(20));
        }

        List<Integer> beforeList = list.stream().sorted().collect(Collectors.toList());

        LOGGER.info("******串行原始list{}", beforeList.toString());

        List<Integer> distinctList = list.stream().distinct().sorted().collect(Collectors.toList());
        LOGGER.info("******去重后list{}", distinctList.toString());

        List<Integer> afterList = list.stream().filter(li->li>10).sorted().collect(Collectors.toList());
        LOGGER.info("******过滤后list{}", afterList.toString());


        //并行及串行对比
        list.clear();
        int seed = 1000000;
        for (int i = 0; i < seed; i++) {
            list.add(rand.nextInt(seed));
        }

        long t0 = System.currentTimeMillis();
        list.stream().sorted().collect(Collectors.toList());
        LOGGER.info("******穿行排序耗时{}ms", System.currentTimeMillis()-t0);

        long t1 = System.currentTimeMillis();
        list.parallelStream().sorted().collect(Collectors.toList());
        LOGGER.info("******并行排序耗时{}ms", System.currentTimeMillis()-t1);
    }
}
