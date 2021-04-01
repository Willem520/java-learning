package willem.base.io.nio;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 15:08
 * @since 1.0.0
 */
public final class NIOClient {
    private static final int DEFAULT_PORT = 12321;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static NIOClientHandler clientHandler;

    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port){
        if (clientHandler != null)
            return;
        clientHandler = new NIOClientHandler(ip,port);
        new Thread(clientHandler,"Server").start();
    }

    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("q"))
            return false;
        clientHandler.sendMsg(msg);
        return true;
    }
}
