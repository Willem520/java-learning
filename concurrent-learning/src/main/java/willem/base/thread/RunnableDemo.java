package willem.base.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableDemo implements Runnable {

    @Override
    public void run() {
        int i = 0;
        do {
            log.info("****** RunnableDemo Say Hello {} ******", i++);
        } while (i < 10);
    }
}
