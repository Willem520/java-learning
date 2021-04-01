package willem.base.thread;


import org.junit.jupiter.api.Test;

public class RunnableDemoTest {
    @Test
    public void runnableTest(){
        new Thread(new RunnableDemo()).start();
    }
}
