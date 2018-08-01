package willem.weiyu.lambda;

/**
 * @author weiyu
 * @description lambda表达式
 * @create 2018/7/31 16:50
 * @since jdk1.8
 */
public class LambdaDemo {

    public static void main(String[] args) {
        //无lambda表达式时
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("****** java without lambda");
            }
        }).start();

        new Thread(() ->{
            int count = 0;
            do {
                System.out.println("****** java lambda"+count++);
            }while (count < 10);
        }).start();
    }
}
