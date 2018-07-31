package willem.weiyu.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author weiyu@gomefinance.com.cn
 * @description
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
        System.out.println("======原始list:"+beforeList.toString());

        List<Integer> distinctList = list.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println("======去重后list:"+distinctList.toString());

        List<Integer> afterList = list.stream().filter(li->li>10).sorted().collect(Collectors.toList());
        System.out.println("======过滤后list:"+afterList.toString());

    }
}
