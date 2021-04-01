package willem.base.thread;

/**
 * @author weiyu
 * @description
 * @create 2018/6/14 11:06
 * @since 1.0.0
 */
public class InterruptDemo {

    public static void main(String[] args) {
        //sleepThread睡眠1000ms
        Thread sleepThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //busyThread一直执行死循环
        Thread busyThread = new Thread(()->{
           while (true){}
        });

        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while (sleepThread.isInterrupted()){}
        System.out.println(String.format("****** sleepThread isInterrupted %s ******", sleepThread.isInterrupted()));
        System.out.println(String.format("****** busyThread isInterrupted %s ******", busyThread.isInterrupted()));
    }
}
