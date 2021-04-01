package willem.base.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列ArrayBlockingQueue
 * LinkedBlockingQueue用法与ArrayBlockingQueue基本相同，两者区别可以参考ArrayList与LinkedList
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();

        Thread.sleep(4000);
    }
}

class Producer implements Runnable {
    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int value;
        boolean isSuccess;
        while(true){
            try {
                value = rand.nextInt(9999)+1;
                isSuccess = blockingQueue.offer(value,5,TimeUnit.SECONDS);
                if (!isSuccess){
                    System.out.println("******队列已满，休息一下******");
                    Thread.sleep(2000);
                    continue;
                }
                System.out.println("======生产成功，value："+value);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}

class Consumer implements Runnable{
    private BlockingQueue blockingQueue;

    public Consumer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Integer value;
        while (true){
            try {
                value = (Integer) blockingQueue.poll();
                if (value == null){
                    System.out.println("******队列已空，休息一下******");
                    Thread.sleep(2000);
                    continue;
                }
                System.out.println("======消费成功，value："+value);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
