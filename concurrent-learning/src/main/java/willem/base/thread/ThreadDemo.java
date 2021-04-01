package willem.base.thread;

public class ThreadDemo extends Thread {

    @Override
    public void run() {
        int i = 0;
        do {
            System.out.println(String.format("****** ThreadDemo Say Hello %s ******", i++));
        } while (i < 10);
    }
}
