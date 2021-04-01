package willem.base.io.aio;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 16:01
 * @since 1.0.0
 */
public final class AIOServer {
    private static final int DEFAULT_PORT = 12321;
    public volatile static long clientCount = 0;
    public static AsyncServerHandler serverHandler;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if (serverHandler != null)
            return;
        serverHandler = new AsyncServerHandler(port);
        new Thread(serverHandler,"server").start();
    }
}
