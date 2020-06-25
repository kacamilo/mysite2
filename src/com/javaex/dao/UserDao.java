package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "webdb";
		private String pw = "webdb";
		
		private void getConnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);

				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
				// System.out.println("접속성공");

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		public void close() {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		// 회원추가
		
		public int insert(UserVo vo) {
			int count = 0;
			getConnection();
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " INSERT INTO users ";
				query += " VALUES (seq_users_no.nextval, ?, ?, ?, ?) ";
				// System.out.println(query);
				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				pstmt.setString(1, vo.getId());		// ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, vo.getPassword());	// ?(물음표) 중 2번째, 순서중요
				pstmt.setString(3, vo.getName());		// ?(물음표) 중 3번째, 순서중요
				pstmt.setString(4, vo.getGender());	// ?(물음표) 중 4번째, 순서중요
				
				count = pstmt.executeUpdate(); // 쿼리문 실행
				
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			
			close();
			return count;
			
		}
		//로그인한 사용자 가져오기
		public UserVo getUser(String id, String password) {
			UserVo vo = null;
			
			getConnection();
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = ""; // 쿼리문 문자열만들기, ? 주의
				query += " select no, name ";
				query += " from users ";
				query += " where id= ? ";
				query += " and password = ? ";
	
				// System.out.println(query);
				
				pstmt = conn.prepareStatement(query); // 쿼리로 만들기
				pstmt.setString(1, id);		// ?(물음표) 중 1번째, 순서중요
				pstmt.setString(2, password);		// ?(물음표) 중 2번째, 순서중요
				
				rs = pstmt.executeQuery();
				
				// 4. 결과처리
				while (rs.next()) {
					int no = rs.getInt("no");
					String name = rs.getString("name");
					
					vo = new UserVo();
					vo.setNo(no);
					vo.setName(name);					
				}				
				
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return vo;
		}
		//회원 수정
		//로그인한 사용자 가져오기
			public UserVo getModify(String id, String password, String name, String gender) {
				UserVo vo = null;
				
				getConnection();
				try {
					// 3. SQL문 준비 / 바인딩 / 실행
					String query = ""; // 쿼리문 문자열만들기, ? 주의
					query += " select no, id, password, name, gender ";
					query += " from users ";
					query += " where id= ? ";
					query += " and password = ? ";
					query += " and name = ? ";
					query += " and gender = ? ";
		
					// System.out.println(query);
					
					pstmt = conn.prepareStatement(query); // 쿼리로 만들기
					pstmt.setString(1, id);		// ?(물음표) 중 1번째, 순서중요
					pstmt.setString(2, password);		// ?(물음표) 중 2번째, 순서중요
					pstmt.setString(3, name);		// ?(물음표) 중 3번째, 순서중요
					pstmt.setString(4, gender);		// ?(물음표) 중 4번째, 순서중요
					
					
					rs = pstmt.executeQuery();
					
					
					
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}
				close();
				return vo;
	
			}	
	}
	
	