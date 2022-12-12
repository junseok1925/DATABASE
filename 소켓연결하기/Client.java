package 소켓연결하기;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			socket=new Socket();
			//1. 소켓 생성
			
			System.out.println("[연결 요청]");
			socket.connect(new InetSocketAddress("localhost",50005));
			//2. 서버에 요청 연결
			
			System.out.println("[연결 성공]");
			//3. 송수신
			
			/* =========================메세지 주고받기============================ */
			
			byte[]bytes = null;
			String message = null;
			
			OutputStream os = socket.getOutputStream();
			//연결된 소캣으로 server로 client를 아웃(보냄)
			message = "hi Server";
			bytes = message.getBytes("utf-8");
			os.write(bytes); //server로 bytes 보냄
			os.flush();
			System.out.println("[데이터 전송함]");
			
			InputStream is = socket.getInputStream();
			bytes = new byte[100];
			int readByteCount = is.read(bytes);
			message = new String(bytes,0,readByteCount,"utf-8");
			System.out.println("[데이터 받음]"+message);
			
			os.close();
			is.close();
			
			/* =========================메세지 주고받기============================ */
			
		}catch (Exception e) {
		}
		if(!socket.isClosed()) {
			try {
				socket.close();
			}catch (Exception e) {
			}
			//4. 연결 종료
		}
	}

}
