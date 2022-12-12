package 채팅하기;

import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			socket = new Socket("localhost",55555);
			System.out.println("[연결성공]");
		}catch (Exception e) {
			System.out.println("[연결실패]");
		}
		
		if(socket != null) {
			RThread rt = new RThread(socket);
			SThread st = new SThread(socket);
			
			rt.start();
			st.start();
			
		}

	}

}
