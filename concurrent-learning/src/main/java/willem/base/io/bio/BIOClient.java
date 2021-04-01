package willem.base.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author weiyu
 * @description
 * @create 2018/6/5 13:58
 * @since 1.0.0
 */
public class BIOClient {
    //默认端口号
    private static final int DEFAULT_SERVER_PORT = 12321;
    private static final String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression) {
        System.out.println("======客户端发送算术表达式：" + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println(expression);
            System.out.println("======客户端接收到返回结果："+in.readLine());
            System.out.println("******");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
