package willem.base.thread.threadPool;

import java.util.Random;

/**
 * @Author weiyu005
 * @Description
 * @Date 2018/12/29 17:25
 */
public class RecursionTask extends java.util.concurrent.RecursiveTask<Boolean> {
    private static final int THRESHOLD = 50;
    private int taskNum;

    public RecursionTask(int taskNum){
        this.taskNum = taskNum;
    }

    @Override
    protected Boolean compute() {
        if (taskNum <= THRESHOLD){
            System.out.println(String.format("****** %s承担了%s份任务 ******", Thread.currentThread().getName(), taskNum));
        } else {
            Random random = new Random();
            int num = random.nextInt(50);

            RecursionTask subTask1 = new RecursionTask(num);
            RecursionTask subTask2 = new RecursionTask(taskNum-num);
            subTask1.fork();
            subTask2.fork();
        }
        return true;
    }
}
