package willem.base.io.nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 14:33
 * @since 1.0.0
 */
public final class NioServer {
    private static final int DEFAULT_PORT = 12321;
    private static NioServerHandler serverHandler;
    private static ExecutorService executor = Executors.newSingleThreadExecutor(r -> new Thread(r,"server"));
    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if (serverHandler != null) {
            return;
        }
        serverHandler = new NioServerHandler(port);
        executor.submit(serverHandler);
    }
}
