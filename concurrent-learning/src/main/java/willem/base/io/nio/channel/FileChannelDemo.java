package willem.base.io.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author weiyu
 * @description
 * @create 2018/5/16 16:00
 * @since 1.0.0
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("E://data.txt","rw");
        FileChannel channel = file.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);
        int byteRead = channel.read(buf);
        while (byteRead != -1){
            System.out.println("Read "+byteRead);
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            buf.clear();
            byteRead = channel.read(buf);
        }
        file.close();
    }
}
