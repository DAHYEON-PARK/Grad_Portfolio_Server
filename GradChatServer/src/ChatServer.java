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
	
	//서버소켓 - 클라이언트의 접속을 받아들이는 클래스
	int port=50000;
	ServerSocket server;
	
	Vector<ServerThread> connectList;
	
	public ChatServer() {
		connectList = new Vector<ServerThread>();
		
		try {
			//서버객체 생성
			server = new ServerSocket(port);
			System.out.println("server 시작");
			
			while(true){
				//접속자가 접속하는지 확인
				//접속자가 있을때까지 대기,지연상태에 있다.
				System.out.println("server while 시작");
				Socket client = server.accept();
				System.out.println("server accept");
				InetAddress inet = client.getInetAddress();
				String ip = inet.getHostAddress();
				System.out.println(ip+"-접속자 발견");
				
				//대화용 쓰레드 생성 및 소켓
				ServerThread serverThread = new ServerThread(connectList, client);
				System.out.println("start");
				serverThread.start();
				
				connectList.add(serverThread);
				System.out.println("현재 접속자수:"+connectList.size());
			}
			
			/*
			//클라이언트의 메세지를 받아들여보자(입력)
			InputStream is = client.getInputStream();
			//한글이 지원되지 않으므로 2바이트씩 처리하는 스트림으로 업그레이드
			InputStreamReader reader;//문자기반스트림(2바이트씩 처리)
			reader = new InputStreamReader(is);
			
			//문자단위로 데이터를 처리하면 효율상 떨어지므로
			//버퍼스트림으로 업그레이드
			//버퍼처리된 문자기반 스트림
			BufferedReader buffer = new BufferedReader(reader);
			
			//클라이언트가 보낸 메시지를 출력해보자(출력)
			OutputStream os = client.getOutputStream();
			//문자기반 출력스트림
			OutputStreamWriter writer = new OutputStreamWriter(os);
			//버퍼처리된 문자기반 출력 스트림
			BufferedWriter bufferWriter = new BufferedWriter(writer);
			
			//int data;
			String data;
			while(true){
				//클라이언트에서 메시지를 받는다.
				//data = is.read();
				//data = reader.read();
				data = buffer.readLine();
				
				//System.out.print((char)data);
				System.out.println(data);
				//클라이언트에게 메시지를 보낸다.
				//os.write((char)data);
				//os.write(data);
				bufferWriter.write(data+"\n");
				//출력스트림안의 데이터를 비우는것이다.
				//혹시라도 남아있을수 있는 데이터를 전부 내보냄
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