package willem.base.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author weiyu
 * @description
 * @create 2018/5/21 10:54
 * @since 1.0.0
 */
public class MyQueue<T> {
   private Object[] data;
   private Lock lock;
   private Condition notEmpty;
   private Condition notFull;
   private int size = 0;
   private int addIndex = 0;
   private int removeIndex = 0;

    public MyQueue(int size) {
        this.data = new Object[size];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public void add(T object){
        lock.lock();
        try{
            while (size == data.length){
                System.out.println("****** 队列已满，请稍等 ******");
                notFull.await();
            }
            data[addIndex] = object;
            if (addIndex == data.length-1){
                addIndex = 0;
            }
            size++;
            notEmpty.signal();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T remove(){
        lock.lock();
        try{
            while (0 == size){
                System.out.println("****** 队列为空，请稍等 ******");
                notEmpty.await();
            }
            Object element = data[removeIndex];
            if (removeIndex == data.length-1){
                removeIndex = 0;
            }
            size--;
            notFull.signal();
            return (T)element;
        }catch (InterruptedException e){
           e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }
}
