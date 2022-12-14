package DB활용회원관리ver2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DB활용회원관리ver2.MemberVO;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		MemberDAO dao = new MemberDAO();

		int menu; // 메뉴선택한 번호 저장 변수

		System.out.println("=====회원관리 프로그램=====");

		while (true) {
			System.out.print("메뉴선택  [  1.회원목록  2.회원등록  3.회원정보 업데이트  4.회원정보 삭제  5.회원정보 조회   6.종료     ]");
			menu = sc.nextInt();

			if (menu == 6) { // 6입력하면 종료 , 위에 적은 이유는 맨아래 default가 작동하기 전에 (case1~5이외의 입력값을 넣으면작동)먼저 실행하기위해
				System.out.println("종료합니다.");
				break;
			}

			switch (menu) {
			case 1:
				List<MemberVO> list = new ArrayList<>(); // 리턴된 컬렉션을 저장하는 공간
				list = dao.list(); // 메서드 호출 결과를 list에 저장

				for (int i = 0; i < list.size(); i++) {
					System.out.print("번호 : " + list.get(i).getMemberno() + "\t");
					System.out.print("아이디 : " + list.get(i).getId() + "\t");
					System.out.println("이름 : " + list.get(i).getName() + "\t");

				}
				break;

			case 2:
				MemberVO vo = new MemberVO();
				System.out.println("회원 등록을 시작합니다.");
				System.out.print("회원 번호 : ");
				vo.setMemberno(sc.nextInt());
				System.out.print("회원 아이디 : ");
				vo.setId(sc.next());
				System.out.print("회원 이름 : ");
				vo.setName(sc.next());

				dao.insert(vo);
				break;

			case 3:
				MemberVO uvo = new MemberVO();
				System.out.println("회원 정보 업데이트를 시작합니다.");
				System.out.print("수정할 회원 번호 : ");
				uvo.setMemberno(sc.nextInt());
				System.out.print("변경할 새로운 회원 아이디 : ");
				uvo.setId(sc.next());
				System.out.print("변경할 새로운 회원 이름 : ");
				uvo.setName(sc.next());

				dao.update(uvo);
				break;

			}

		}

	}
}