package willem.base.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author weiyu005@ke.com
 * @Description 自旋锁：指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 * @Date 2021/1/26 22:26
 */
public class Spinlock {
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null, thread)){

        }
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        sign.compareAndSet(thread, null);
    }
}
