package willem.base.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列DelayQueue
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue delayQueue = new DelayQueue();
        DelayObject object = new DelayObject();
        delayQueue.put(object);

        System.out.println(delayQueue.take());
    }
}

class DelayObject implements Delayed{

    public long getDelay(TimeUnit unit) {
        return 0;
    }

    public int compareTo(Delayed o) {
        return 0;
    }
}
