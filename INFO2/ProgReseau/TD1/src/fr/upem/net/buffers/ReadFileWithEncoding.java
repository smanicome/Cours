package fr.upem.net.buffers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadFileWithEncoding {

    private static void usage(){
        System.out.println("Usage: ReadFileWithEncoding charset filename");
    }

    private static String stringFromFile(Charset cs,Path path) throws IOException {
        FileChannel in = FileChannel.open(path, StandardOpenOption.READ);
        var size = in.size();

        ByteBuffer buff = ByteBuffer.allocate((int) size);
        in.read(buff); // TODO read until buff is full
        buff.flip();
        var cb = cs.decode(buff);

        return cb.toString();
    }

    public static void main(String[] args) throws IOException {
        if (args.length!=2){
            usage();
            return;
        }
        Charset cs=Charset.forName(args[0]);
        Path path=Paths.get(args[1]);
        System.out.print(stringFromFile(cs,path));
    }


}
