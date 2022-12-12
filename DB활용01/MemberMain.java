package DB활용01;

import java.util.List;

public class MemberMain {

	public static void main(String[] args) {

		// insert 추가할 데이터를 저장하는 객체
		MemberVO vo = new MemberVO();
		vo.setMemberno(4);
		vo.setId("벌레");
		vo.setName("베인");

		MemberDAO dao = new MemberDAO();

		int r = dao.insert(vo);

		if (r > 0) {
			System.out.println("회원 등록 성공");
		}

		// select 검색된 결과를 저장하는 객체
		MemberVO rvo = new MemberVO();
		rvo = dao.selectOne(5);

		System.out.println("==정보출력==");
		System.out.println("번호 : " + rvo.getMemberno());
		System.out.println("아이디 : " + rvo.getId());
		System.out.println("이름 : " + rvo.getName());

		// ========================전체 회원 정보 조회=======================

		System.out.println("=====전체회원 정보=====");
		List<MemberVO> list = dao.list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println("번호 : " + list.get(i).getMemberno() + 
								"\t 아이디 : " + list.get(i).getId() + 
								"\t 이름: " + list.get(i).getName());
		}

		// ========================회원 정보 삭제==========================

		r = dao.delete(4);
		if (r > 0) {
			System.out.println("삭제성공");
		}

		System.out.println("=====전체회원 정보=====");
		list = dao.list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println("번호 : " + list.get(i).getMemberno() + 
								"\t 아이디 : " + list.get(i).getId() + 
								"\t 이름: " + list.get(i).getName());

		}

		// ========================회원 정보 업데이트==========================
		// update
		vo.setMemberno(1); // 변경할 member 번호
		vo.setId("악마"); // id를 저렇게 바꾸겠다
		vo.setName("티모"); // name을 저렇게 바꾸겠다

		r = dao.update(vo);
		if (r > 0) {
			System.out.println("수정완료");
		}

		System.out.println("=====전체회원 정보=====");
		list = dao.list();

		for (int i = 0; i < list.size(); i++) {
			System.out.println("번호 : " + list.get(i).getMemberno() + 
								"\t 아이디 : " + list.get(i).getId() + 
								"\t 이름: " + list.get(i).getName());

		}
		
		
		
	}
}