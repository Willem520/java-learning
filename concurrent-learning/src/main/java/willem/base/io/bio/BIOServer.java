package willem.base.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 13:33
 * @since 1.0.0
 */
public final class BIOServer {
    //默认端口号
    private static final int DEFAULT_PORT = 12321;
    //单例的ServerSocket
    private static ServerSocket server;
    //使用线程池来管理线程，提高线程利用率
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    //根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
    public static void start() throws IOException {
        //使用默认值
        start(DEFAULT_PORT);
    }

    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步
    public synchronized static void start(int port)throws IOException {
        if (server != null)
            return;
        try{
            //通过构造函数创建ServerSocket，如果端口合法且空闲，服务端坚挺成功
            server = new ServerSocket(port);
            System.out.println("======服务器已启动，端口号："+port);
            //通过无限循环监听客户端接入，将阻塞在accept操作上
            while (true){
                Socket socket = server.accept();
                //当有新的客户端接入时，会执行下面的代码，然后创建一个新的线程处理这条Socket链路
                executorService.execute(new BIOServerHandler(socket));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (server != null){
                System.out.println("======服务器已关闭");
                server.close();;
                server = null;
            }
        }
    }
}
