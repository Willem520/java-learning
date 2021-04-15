package willem.base.io.nio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 15:08
 * @since 1.0.0
 */
public final class NioClient {
    private static final int DEFAULT_PORT = 12321;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static NioClientHandler clientHandler;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 5000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(128));

    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port){
        if (clientHandler != null) {
            return;
        }
        clientHandler = new NioClientHandler(ip,port);
        executor.submit(clientHandler);
    }

    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("exit")) {
            return false;
        }
        clientHandler.sendMsg(msg);
        return true;
    }
}
