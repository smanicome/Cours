package fr.upem.net.buffers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class StoreWithByteOrder {
	private static final int BUF_SIZE = 1024;

	public static void usage() {
		System.out.println("StoreWithByteOrder [LE|BE] filename");
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			usage();
			return;
		}
		Path pOut = Paths.get(args[1]);
		FileChannel out = FileChannel.open(pOut, StandardOpenOption.WRITE, StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
		ByteBuffer buff = ByteBuffer.allocate(BUF_SIZE);

		switch (args[0].toUpperCase()) {
			case "LE":
				buff.order(ByteOrder.LITTLE_ENDIAN);
				break;
			case "BE":
				break;
			default:
				System.out.println("Unrecognized option : "+args[0]);
				usage();
				return;
		}
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLong()) {
			long l = sc.nextLong();
			buff.putLong(l);
			buff.flip();
			out.write(buff);
			buff.clear();
		}
		out.close();
		sc.close();
	}
}