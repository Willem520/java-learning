package willem.base.thread.threadPool;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/29 15:15
 */
public class SimpleTask implements Runnable {
    private String name;

    public SimpleTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(String.format("****** simple job-[%s] working ******", this.name));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}
