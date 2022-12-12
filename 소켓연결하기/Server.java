package 소켓연결하기;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket();
			// 1.소캣 생성
			serverSocket.bind(new InetSocketAddress("localHost", 50005));
			// 2.소켓 빈공간에 ip,port를 지정

			while (true) {
				// 3.클라이언트 요청 대기(서버는 기본으로 24시간 대기 상태 비슷한 환경을 만들기위해 무한반복문 사용)
				System.out.println("[연결대기]");

				Socket socket = serverSocket.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				// 4.요청 승인, 데이터 송수신 소켓 생성

				System.out.println("[연결수락] : " + isa.getHostName());
				// 5.송수신

				/* =========================메세지 주고받기============================ */
				
				byte[] bytes = null; // byte단위로 주고받기때문

				String message = null; // byte를 문자열로 형변환

				InputStream is = socket.getInputStream();
				bytes = new byte[100]; // client에서 받은 bytes를 배열방에 저장
				int readByteCount = is.read(bytes);
				message = new String(bytes, 0, readByteCount, "utf-8");
				// 저장된 bytes를 문자열(String message)형태로 바꿈
				System.out.println("[데이터 받음] : " + message);

				OutputStream os = socket.getOutputStream();
				message = "hi Client";
				bytes = message.getBytes("utf-8");
				os.write(bytes);
				os.flush();
				System.out.println("[데이터 전송함]");

				is.close();
				os.close();
				socket.close();

				/* =========================메세지 주고받기============================ */
				
			}
		} catch (Exception e) {
			System.out.println("[연결실패]");
		}
		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} catch (Exception e) {

			}
		}
	}

}
