package willem.base.io.nio;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 14:33
 * @since 1.0.0
 */
public final class NIOServer {
    private static final int DEFAULT_PORT = 12321;
    private static NIOServerHandler serverHandler;

    public static void start(){
        start(DEFAULT_PORT);
    }

    public static synchronized void start(int port){
        if (serverHandler != null)
            return;
        serverHandler = new NIOServerHandler(port);
        new Thread(serverHandler,"server").start();
    }
}
