package com.horseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BangDAO {
	
	//3. 방명록 작성
	public int insertBang(String id,String title,String content){
		
		int result = 0;
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			
			sql = "insert into bang (no,id,nal,title,content) ";
			sql+= "values ((select nvl(max(no+1),1) from bang),?,sysdate,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;

	}
	
	//4. 방명록 출력
	public List<BangDTO> getList(){
		
		List<BangDTO> lists = new ArrayList<BangDTO>();
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql;
		
		try {
			
			sql = "select no,B.id,nal,title,content from horgi A, bang B where A.id = B.id";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BangDTO dto = new BangDTO();
				
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setNal(rs.getString("nal"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
		
	}
	
	//5. 방명록 삭제
	public int deleteBang(String no, String id){
		
		int result = 0;
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			
			sql = "delete bang where no like ? and id like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, no);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;

	}

}
