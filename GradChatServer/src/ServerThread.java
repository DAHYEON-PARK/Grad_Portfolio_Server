import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class ServerThread extends Thread{
	Socket client;
	BufferedReader buffer;
	BufferedWriter bufferWriter;
	Vector<ServerThread> connectList;
	
	public ServerThread(Vector<ServerThread> connectList, Socket socket) {
		this.connectList = connectList;
		this.client = socket;
		try {
			buffer = new BufferedReader(new InputStreamReader((client.getInputStream())));
			bufferWriter = new BufferedWriter(new OutputStreamWriter((client.getOutputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true){
			String msg = listen();
			send(msg);
		}
	}
	
	
	public String listen(){
		String msg="";
		try {
			msg= buffer.readLine();
			System.out.println("msg:"+msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	

	public void send(String msg){
		try {
			for(int i=0;i<connectList.size();i++){
				ServerThread st = connectList.get(i);
				
				st.bufferWriter.write(msg+"\n");
				st.bufferWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}