package willem.weiyu.interfaceMethod;

/**
 * @author weiyu
 * @description 接口中的static方法不能被继承，也不能被实现类调用，只能被自身调用
 * @create 2018/8/1 15:37
 * @since 1.0.0
 */
public class StaticMethodDemo {

    protected static class Basketball implements Ball{

    }

    public static void main(String[] args) {
        Basketball basket = new Basketball();
        //basket.play();
        Ball.play();
    }
}

interface Ball{
    static void play(){
        System.out.println("play ball");
    }
}
