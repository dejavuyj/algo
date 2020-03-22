package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends Thread {
	public void run() {
		try (Selector selector = Selector.open();
				ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// ����Selector��Channel
			serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),
					8888));
			serverSocket.configureBlocking(false);
			// ע�ᵽSelector����˵����ע��
			serverSocket.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				selector.select();// �����ȴ�������Channel�����ǹؼ���֮һ
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iter = selectedKeys.iterator();
				while (iter.hasNext()) {
					SelectionKey key = iter.next();
					// ����ϵͳ��һ��������о���״̬���
					sayHelloWorld((ServerSocketChannel) key.channel());
					iter.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sayHelloWorld(ServerSocketChannel server) throws IOException {
		try (SocketChannel client = server.accept();) {
			client.write(Charset.defaultCharset().encode("Hello world!"));
		}
	}

	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.start();
		try (Socket client = new Socket(InetAddress.getLocalHost(),
				8888)) {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			bufferedReader.lines().forEach(s -> System.out.println(s));
		}
	}
}
