package willem.weiyu.clazz;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author weiyu
 * @description Optinal类基本使用
 * @create 2018/8/2 9:45
 * @since 1.0.0
 */
public class OptionalDemo {

    public static void main(String[] args) {
        String str = "hello";
        Optional<String> strValue = Optional.of(str);

        System.out.println("======part1");
        if(str != null){
            System.out.println(str);
        }

        System.out.println("======part2");
        if(strValue.isPresent()){
            System.out.println(strValue.get());
        }

        System.out.println("======part3");
        strValue.ifPresent(s -> System.out.println(s));

        Optional<String> op = Optional.ofNullable(null);
        System.out.println("======part4");
        System.out.println(op.orElse("hahahaha"));

        System.out.println("======part5");
        System.out.println(op.orElseGet(() -> "hehehe"));

        Optional<String> opt = Optional.ofNullable("nihao");
        System.out.println("======part6");
        System.out.println(opt.map(m -> m + "123").orElseGet(() -> "world"));

        System.out.println("======part7");
        Province province = new Province();
        Optional<Province> parentVal = Optional.ofNullable(province);
        System.out.println(parentVal.map(m -> m.getCities()).orElse(Collections.emptyList()));
    }
}

class Province{
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
