package willem.base.interfaceMethod;

/**
 * @author weiyu
 * @description 如果一个接口只有一个抽象方法，则该接口称之为函数式接口，添加@FunctionalInterface注解
 * 函数式接口允许定义默认方法
 * 函数式接口允许定义静态方法
 * 函数式接口允许定义java.lang.Object里的public方法
 * @create 2018/8/1 16:01
 * @since jdk1.8
 */
public class FunctionalInterfaceDemo {

    public static void main(String[] args) {
        Greeting greet = message -> System.out.println("hello,"+message);
        greet.say("jack");
    }
}

@FunctionalInterface
interface Greeting{
    void say(String message);
}
