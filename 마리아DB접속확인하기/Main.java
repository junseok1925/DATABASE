package 마리아DB접속확인하기;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

   public static void main(String[] args) {
      
      Connection conn = null; //연결해주는 커넥션 객체 생성
      
      try {
         /**아래 고정 코드(계속 사용 예정) */
         Class.forName("org.mariadb.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javadb", "root", "rkd785985@");
         
   
         
      } catch (Exception e) {
         
      } finally {
         if(conn != null) {
            try {
               conn.close();
            } catch(Exception e) {

            }
         }
         
      }
      
      if(conn != null)
         System.out.println("데이터베이스 접속");

   }

}