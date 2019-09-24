package com.horseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GambleDAO {
	
	//0.1 select id
	
	public String selectId(String id){		
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select id from horgi where id like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();		

			if(rs.next()){

				id = null;
					
			}

			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			try {pstmt.close();} catch (Exception e2) {}
			try {DBhor.close();} catch (Exception e2) {}
		}
		
		return id;
		
	}
	
	//0.2 select jumin
	public String selectJumin(String jumin){		
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select id from horgi where name like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, jumin);
			
			rs = pstmt.executeQuery();
			

			if(rs.next()){
					
				GambleDTO dto = new GambleDTO();
					
				dto.setId(rs.getString("id"));	
				
				jumin = null;
					
			}

			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			try {pstmt.close();} catch (Exception e2) {}
			try {DBhor.close();} catch (Exception e2) {}
		}
		
		return jumin;
		
	}
	
	//1. insert
	public int insertData(GambleDTO dto){
		
		int result = 0;
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			
			sql = "insert into horgi (id,pwd,name,jumin,tel) ";
			sql+= "values (?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getJumin());
			pstmt.setString(5, dto.getTel());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
	
		} catch (Exception e) {
			try {pstmt.close();} catch (Exception e2) {}
			try {DBhor.close();} catch (Exception e2) {}
		}
		
		return result;
		
	}
	
	//update
	public GambleDTO getList(String id, String jumin){
		
		GambleDTO dto = null;
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			
			sql = "select id,pwd,name,jumin,tel ";
			sql+= "from horgi where id like ? and jumin like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, jumin);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new GambleDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				
			}
			
		} catch (Exception e) {
			try {pstmt.close();} catch (Exception e2) {}
			try {DBhor.close();} catch (Exception e2) {}
		}
		
		return dto;
		
	}
	
	//login
	public GambleDTO login(String id,String pwd){
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GambleDTO dto = null;
		String sql;
		
		try {
			
			sql = "select id,name from horgi where id like ? and pwd like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new GambleDTO();
				
				dto.setId(rs.getString("id"));

			}	
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;

	}
}
