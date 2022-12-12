package 채팅하기;

import java.io.DataInputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {

	private final Socket socket;

	public ReceiveThread(Socket socket) { // 생성자 만들기
		this.socket = socket;
	}

	public void run() { // Thread 사용하기위해 run메서드 생성
		try {
			DataInputStream tmpbuf = new DataInputStream(socket.getInputStream());
			String message; // 받는 문자 저장하는 변수

			while (true) { // 메세지를 주고받기를 무한 루프로 이용
				message = tmpbuf.readUTF();
				System.out.println("상대방 : " + message);

			}
		} catch (Exception e) {
			System.out.println("[연결 종료]");
		}

	}

}
