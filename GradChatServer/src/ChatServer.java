import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	
	int port=50000;
	ServerSocket server;
	
	Vector<ServerThread> connectList;
	
	public ChatServer() {
		connectList = new Vector<ServerThread>();
		
		try {

			server = new ServerSocket(port);
			System.out.println("server start");
			
			while(true){
				System.out.println("server while start");
				Socket client = server.accept();
				System.out.println("server accept");
				InetAddress inet = client.getInetAddress();
				String ip = inet.getHostAddress();
				System.out.println(ip+"- client connect");
				
				ServerThread serverThread = new ServerThread(connectList, client);
				System.out.println("start");
				serverThread.start();
				
				connectList.add(serverThread);
				System.out.println("client num : "+connectList.size());
			}
			
			/*
			//Ŭ���̾�Ʈ�� �޼����� �޾Ƶ鿩����(�Է�)
			InputStream is = client.getInputStream();
			//�ѱ��� �������� �����Ƿ� 2����Ʈ�� ó���ϴ� ��Ʈ������ ���׷��̵�
			InputStreamReader reader;//���ڱ�ݽ�Ʈ��(2����Ʈ�� ó��)
			reader = new InputStreamReader(is);
			
			//���ڴ����� �����͸� ó���ϸ� ȿ���� �������Ƿ�
			//���۽�Ʈ������ ���׷��̵�
			//����ó���� ���ڱ�� ��Ʈ��
			BufferedReader buffer = new BufferedReader(reader);
			
			//Ŭ���̾�Ʈ�� ���� �޽����� ����غ���(���)
			OutputStream os = client.getOutputStream();
			//���ڱ�� ��½�Ʈ��
			OutputStreamWriter writer = new OutputStreamWriter(os);
			//����ó���� ���ڱ�� ��� ��Ʈ��
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			
			//int data;
			String data;
			while(true){
				//Ŭ���̾�Ʈ���� �޽����� �޴´�.
				//data = is.read();
				//data = reader.read();
				data = buffer.readLine();
				
				//System.out.print((char)data);
				System.out.println(data);
				//Ŭ���̾�Ʈ���� �޽����� ������.
				//os.write((char)data);
				//os.write(data);
				bufferWriter.write(data+"\n");
				//��½�Ʈ������ �����͸� ���°��̴�.
				//Ȥ�ö� ���������� �ִ� �����͸� ���� ������
				//os.flush();
				bufferWriter.flush();
			}*/
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		new ChatServer();
		//server = new ServerSocket(port);
			
	}
}