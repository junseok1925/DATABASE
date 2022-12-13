package DB활용회원관리1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	Connection conn = null; // 접속 연결 객체 생성
	Statement stmt = null; // Statement 잘 사용안함
	ResultSet rs = null; // 값을 저장해둘 객체 생성

	// 생성자
	public MemberDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javadb", "root", "rkd785985@");

			/**
			 * 코드 작성 방법 = DriverManager.getConnection(
			 * "jdbc:mariadb://127.0.0.1:3306/dbName", "userId", "userPassword");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// db에 insert 기능 간소화 작업
	public int insert(MemberVO vo) {
		int result = 0;

		try {
			String sql = "insert into member values(" + vo.getMemberno() + ", '" + vo.getId() + "','" + vo.getName()
					+ "')";

			stmt = conn.createStatement(); // 공간할당
			result = stmt.executeUpdate(sql); // (sql)문 추가&실행
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// db에 select 기능 간소화 작업
	public MemberVO selectOne(int memberno) {

		MemberVO vo = new MemberVO();

		try {
			String sql = "select * from member where memberno=" + memberno;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				vo.setMemberno(rs.getInt("memberno"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public List<MemberVO> list() {
		List<MemberVO> list = new ArrayList<>();

		try {
			String sql = "select*from member";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

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

	public int delete(int memberno) {
		int result = 0;

		try {
			String sql = "delete from member where memberno=" + memberno;

			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(MemberVO vo) {
	      int result = 0;
	                  // 번호에 해당하는 id와 이름을 변경
	      try {         //update member set id='abc', name='OOO' where memberno=2
	         String sql = "update member set id='" 
	                           + vo.getId() + "', name='" + vo.getName() 
	                           + "' where memberno=" + vo.getMemberno();
	         
	         stmt = conn.createStatement();
	         result = stmt.executeUpdate(sql);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return result;
	   }
	   
	   public void close() {
	      if(rs != null) {
	         try {
	            rs.close();
	         } catch(Exception e) {
	            
	         }
	      }
	      
	      if(stmt != null) {
	         try {
	            stmt.close();
	         } catch(Exception e) {
	            
	         }
	      }
	      
	      if(conn != null) {
	         try {
	            conn.close();
	         } catch(Exception e) {
	            
	         }
	      }
	   }
	}