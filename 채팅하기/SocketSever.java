package 채팅하기;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketSever {

	public static void main(String[] args) throws Exception{
		
		ServerSocket serverSocket = new ServerSocket(55555);
		
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("ip : "+socket.getInetAddress()+"와 연결 성공");
			
			ReceiveThread rt = new ReceiveThread(socket); //보내는 변수
			SendThread st = new SendThread(socket);		  //받는 변수
			
			rt.start();
			st.start();
			
			
			
		}

	}

}
