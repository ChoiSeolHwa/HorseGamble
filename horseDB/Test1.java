package com.horseDB;

import java.sql.Connection;

public class Test1 {

	public static void main(String[] args) {
		
		Connection conn = DBhor.getconConnection();
						//class��.getConnection();
		
		if(conn==null){
			
			System.out.println("�����ͺ��̽� ���� ����!!");
			System.exit(0);
			
		}
		
		System.out.println("�����ͺ��̽� ���� ����!!");
		
		DBhor.close();		

	}

}
