package willem.base.thread.threadPool;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author weiyu
 * @description
 * @create 2018/5/24 11:31
 * @since 1.0.0
 */
public class LoopTask implements Runnable {
    protected AtomicBoolean allowRun = new AtomicBoolean(true);
    private String name;

    public LoopTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            if (!allowRun.get()){
                break;
            }
            runJob();
        }
    }

    public void stop() {
        System.out.println(String.format("****** loop job-[%s] stop ******", this.name));
        allowRun.compareAndSet(true, false);
    }

    private void runJob(){
        System.out.println(String.format("****** loop job-[%s] working ******", this.name));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
