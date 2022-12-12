package 채팅하기;

import java.io.DataInputStream;
import java.net.Socket;

//받는 클라이언트클래스
public class RThread extends Thread {

	private final Socket socket;

	public RThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String message;

		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());

			while (true) {
				message = dis.readUTF();

				System.out.println("상대방 : " + message);
			}
		} catch (Exception e) {

		}

	}

}
