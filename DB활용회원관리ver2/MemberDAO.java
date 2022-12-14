package DB활용회원관리ver2;

//데이터베이스의 데이터를 주고받는 클래스

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	// PreparedStatement 기능확장 (재사용면에서 매우 편리함) / 단점으로는 코드가 길어짐

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 생성자

	public MemberDAO() {
		try { // 마리아db 데이터베이스에 연결
			Class.forName("org.mariadb.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javadb", "root", "java1234");
		} catch (Exception e) {
			e.printStackTrace(); // Driver를 못 찾았을때 예외처리
		}
	}

	// 메서드 생성

	// 전체 레코드를 조회하는 메서드

	public List<MemberVO> list() {
		List<MemberVO> list = new ArrayList<>();

		try {
			String sql = "select*from member";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO vo = new MemberVO();

				vo.setMemberno(rs.getInt("memberno"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/* ====================회원정보 입력=================== */

	public void insert(MemberVO vo) {
		try {
			String sql = "insert into member values(?,?,?)";

			pstmt = conn.prepareStatement(sql);

			// "insert into member values(?,?,?)"의 방순서마다 어디에 뭐가 들어갈지 정하기
			pstmt.setInt(1, vo.getMemberno()); // 1번방은 no
			pstmt.setString(2, vo.getId()); // 2번방은 id
			pstmt.setString(3, vo.getName()); // 3번방은 name

			rs = pstmt.executeQuery();
			System.out.println("회원 등록 성공!");
		} catch (Exception e) {
			System.out.println("회원 등록 실패!");
			e.printStackTrace();
		}
	}

/* ====================회원정보 수정 업데이트============= */

	public void update(MemberVO vo) {
		try {
			String sql = "update member set id=?,name=? where memberno=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setInt(3, vo.getMemberno());

			rs = pstmt.executeQuery();
			System.out.println("회원 정보 업데이트 성공!");

		} catch (Exception e) {
			System.out.println("회원 정보 업데이트 실패!");
			e.printStackTrace();
		}

	}
	
}