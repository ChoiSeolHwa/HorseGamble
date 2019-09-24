package com.horseDB;

import java.util.Calendar;

public class GambleException extends Thread{

	public void inputIdcheck(String str) throws Exception{
		
		char ch;
		int mat, eng, ent;
		
		mat = 0;
		eng = 0;
		ent = 0;
		
		if(str.length()<5 || str.length()>10){
			throw new Exception("아이디 입력 길이 오류");
		}
		
		for(int i = 0;i<str.length();i++){
			
			ch = str.charAt(i);
							
				
			if((ch>=65&&ch<=90)||(ch>=97&&ch<=122)){
				eng = 1;
			}
				
			if((ch>=48&&ch<=57)){
				mat = 1;
			}
			
			if((ch>=33&&ch<=47)||(ch>=58&&ch<=64)||(ch>=91&&ch<=96)||(ch>=123&&ch<=126)){
				ent = 1;
			}

		}
		
		if(eng == 0)
		{
			throw new Exception("영어도 입력하시오.");
		}
		
		if(mat == 0)
		{
			throw new Exception("숫자도 입력하시오.");
		}	
		
		if(ent == 1)
		{
			throw new Exception("특수문자는 입력하지 마시오.");
		}
		

	}

	public void inputPwCheck(int s) throws Exception{

		if(s<=0){

			throw new Exception("비밀번호가 동일하지 않습니다!");

		}

	}

	public void inputGdcheck(int s) throws Exception{

		if(s<=0){

			throw new Exception("성별은 Male 또는 Female 만 가능합니다");

		}

	}
	
	public void inputJuCheck(int s) throws Exception{
		HorseImage hi=new HorseImage();
		Calendar now = Calendar.getInstance();
		int y, age;
		
		y = now.get(Calendar.YEAR);
		
		age = y-s+1;
				
		if(!(age>=20 &&age<=100)){
			
			throw new Exception();

		}
		
	}
	
	public void inputJaCheck(String jumin) throws Exception{
		
		if(jumin.length()!=14){
			throw new Exception("주민번호 길이 오류 ((-) 포함 14자리)!!");
		}
		
	}

}
