package willem.weiyu.interfaceMethod;

/**
 * @author weiyu
 * @description default方法可以被子接口继承亦可被其实现类所调用
 * @create 2018/8/1 13:28
 * @since jdk1.8
 */
public class DefaultMethodDemo {

    protected static class Sedan implements Car{

        @Override
        public void start() {
            System.out.println("the car has started");
        }

        @Override
        public void stop() {
            System.out.println("the car has stopped");
        }
    }

    public static void main(String[] args) {
        Sedan car = new Sedan();
        car.start();
        car.run();
        car.stop();
    }
}

interface Car{
    void start();

    void stop();

    default void run(){
        System.out.println("the car is running!");
    }
}
