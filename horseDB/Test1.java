package com.horseDB;

import java.sql.Connection;

public class Test1 {

	public static void main(String[] args) {
		
		Connection conn = DBhor.getconConnection();
						//class명.getConnection();
		
		if(conn==null){
			
			System.out.println("데이터베이스 연결 실패!!");
			System.exit(0);
			
		}
		
		System.out.println("데이터베이스 연결 성공!!");
		
		DBhor.close();		

	}

}
