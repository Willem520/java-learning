package willem.weiyu.defaultMethod;

/**
 * @author weiyu
 * @description 默认方法
 * @create 2018/8/1 13:28
 * @since jdk1.8
 */
public class DefaultMethodDemo {

    protected static class Sedan implements Car{

        @Override
        public void start() {
            System.out.println("the car has started");
            run();
        }

        @Override
        public void stop() {
            System.out.println("the car has stopped");
        }
    }

    public static void main(String[] args) {
        Sedan car = new Sedan();
        car.start();
    }
}

interface Car{
    void start();

    void stop();

    default void run(){
        System.out.println("the car is running!");
    }
}
