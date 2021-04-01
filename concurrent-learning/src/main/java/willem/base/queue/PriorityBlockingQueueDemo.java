package willem.base.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级队列PriorityBlockingQueue
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<PriorityObject> priorityBlockingQueue = new PriorityBlockingQueue();
        priorityBlockingQueue.put(new PriorityObject("1", 23));
        priorityBlockingQueue.put(new PriorityObject("2",29));
        priorityBlockingQueue.put(new PriorityObject("3", 18));

        PriorityObject obj = priorityBlockingQueue.take();
        //按照年龄进行优先级排序
        System.out.println("id:"+obj.getId()+",age:"+obj.getAge());
    }
}

class PriorityObject implements Comparable<PriorityObject>{
    private String id;
    private int age;

    public PriorityObject(){

    }

    public PriorityObject(String id, int age){
        this.id = id;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int compareTo(PriorityObject o) {
        return this.age - o.getAge();
    }
}
