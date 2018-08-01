package willem.weiyu.stream;

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
        System.out.println("======串行原始list:"+beforeList.toString());

        List<Integer> distinctList = list.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println("======去重后list:"+distinctList.toString());

        List<Integer> afterList = list.stream().filter(li->li>10).sorted().collect(Collectors.toList());
        System.out.println("======过滤后list:"+afterList.toString());


        //并行及串行对比
        list.clear();
        int seed = 1000000;
        for (int i = 0; i < seed; i++) {
            list.add(rand.nextInt(seed));
        }

        long t0 = System.currentTimeMillis();
        list.stream().sorted().collect(Collectors.toList());
        long t1 = System.currentTimeMillis();
        System.out.println("======串行排序耗时"+(t1-t0)+"ms");

        long t2 = System.currentTimeMillis();
        list.parallelStream().sorted().collect(Collectors.toList());
        long t3 = System.currentTimeMillis();
        System.out.println("======并行排序耗时"+(t3-t2)+"ms");
    }
}
