package willem.base.io.aio;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 16:32
 * @since 1.0.0
 */
public final class AIOClient {
    private static final int DEFAULT_PORT = 12321;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static AsyncClientHandler clientHandler;

    public static void start(){
        start(DEFAULT_HOST,DEFAULT_PORT);
    }

    public static synchronized void start(String ip,int port){
        if(clientHandler!=null)
            return;
        clientHandler = new AsyncClientHandler(ip,port);
        new Thread(clientHandler,"Client").start();
    }

    public static boolean sendMsg(String msg){
        if (msg.equals("q"))
            return false;
        clientHandler.sendMsg(msg);
        return true;
    }
}
