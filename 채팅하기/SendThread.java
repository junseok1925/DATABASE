package 채팅하기;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {

	private final Socket socket;
	Scanner sc = new Scanner(System.in);

	public SendThread(Socket socket) { // Thread사용으로 run메서드 생성
		this.socket = socket;
	}

	public void run() {
		try {
			DataOutputStream sendWriter = new DataOutputStream(socket.getOutputStream());
			String message;

			while (true) {
				message = sc.nextLine();
				sendWriter.writeUTF(message);
				sendWriter.flush();
			}
		} catch (Exception e) {

		}

	}
}
