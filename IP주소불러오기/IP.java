package IP주소불러오기;
// ip주소 불러오기
import java.net.InetAddress;

public class IP {

	public static void main(String[] args) {
		try {
			InetAddress local = InetAddress.getLocalHost();
			System.out.println("내 컴퓨터 IP주소 : " + local.getHostAddress());

			InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
			//IP주소가 1개가 아닐수도 있기에 배열을 사용
			for (InetAddress r : iaArr) {
				System.out.println("네이버 IP 주소 : " + r.getHostAddress());
			}
		} catch (Exception e) {

		}

	}
}
