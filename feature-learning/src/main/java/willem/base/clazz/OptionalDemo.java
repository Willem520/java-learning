package willem.base.clazz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author weiyu
 * @description Optinal类基本使用
 * @create 2018/8/2 9:45
 * @since jdk1.8
 */
public class OptionalDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalDemo.class);

    private static class Province{
        private String name;
        private List<String> cities;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCities() {
            return cities;
        }

        public void setCities(List<String> cities) {
            this.cities = cities;
        }
    }

    public static void main(String[] args) {
        String str = "hello";
        Optional<String> strValue = Optional.of(str);

        System.out.println("======part1");
        if(str != null){
            LOGGER.info("******{}", str);
        }

        System.out.println("======part2");
        if(strValue.isPresent()){
            LOGGER.info("******{}", strValue.get());
        }

        System.out.println("======part3");
        strValue.ifPresent(System.out::println);

        Optional<String> op = Optional.ofNullable(null);
        System.out.println("======part4");
        System.out.println(op.orElse("hahahaha"));

        System.out.println("======part5");
        LOGGER.info("{}", op.orElseGet(() -> "hahaha"));

        Optional<String> opt = Optional.ofNullable("nihao");
        System.out.println("======part6");
        LOGGER.info("", opt.map(m -> m + "123").orElse("world"));

        System.out.println("======part7");
        Province province = new Province();
        Optional<Province> parentVal = Optional.ofNullable(province);
        LOGGER.info("{}", parentVal.map(m -> m.getCities()).orElse(Collections.emptyList()));
    }
}