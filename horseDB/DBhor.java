package com.horseDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBhor {
	
	private static Connection horseDB;
	
	public static Connection getconConnection(){
		
		if(horseDB==null){
			
			try {
				String url="jdbc:oracle:thin:@192.168.16.3:1521:testDB";
				
				String user="horse";
				String password="123";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				horseDB=DriverManager.getConnection(url,user,password);
				
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		
		return horseDB;
		
	}
	
	public static void close(){
		
		if(horseDB!=null){
			try {
				if(!horseDB.isClosed()){
					horseDB.close();
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		horseDB=null;
		}
	}
	
	
	

}