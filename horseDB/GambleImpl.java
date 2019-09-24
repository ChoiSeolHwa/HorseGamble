package com.horseDB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GambleImpl extends Thread implements Gamble {

	Scanner sc = new Scanner(System.in); 
	BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	HorseImage hi = new HorseImage();
	HorseMain hm = new HorseMain();
	GambleDAO dao = new GambleDAO();
	
	String id = null; //id 확인용

	@Override //로그인
	public void login(){
		
		HorLogin hl = new HorLogin();
	
		String pwd = null;; //pwd 확인용
		
		try {
		
			System.out.print("아이디 : ");
			id = sc.next();
			
			System.out.print("비밀번호 : ");
			pwd = sc.next();
			
			GambleDTO dto = dao.login(id,pwd);
			
			if(dto!=null){
				
				for(int i = 0; i < 20; ++i){ //공간생성
					System.out.println();
				}
				System.out.println("		            \\\\\\///             ");
				System.out.println("		           / _  _ \\            ");
				System.out.println("		         (| (.)(.) |)          ");
				System.out.println("		.------.OOOo--()--oOOO.------. ");
				System.out.println("		|                            | ");
				System.out.println("		" + id + "   님이 입장하셨습니다!!   ");
				System.out.println("		|                            | ");
				System.out.println("		'------.oooO-----------------' ");
				System.out.println("		        (   )   Oooo.          ");
				System.out.println(" 		        \\ (    (   )          ");
				System.out.println(" 		         \\_)    ) /           ");
				System.out.println("   			       (_/            ");
			
				
				for(int i = 0; i < 15; ++i){ //공간생성
					System.out.println();
				}
				
				try {
					sleep(2000);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				
				hl.Hor(id);
			
			}else{
				System.out.println("로그인 실패하셨습니다!!");
				return;
			}
		} catch (Exception e) {
		System.out.println(e.toString());
		}

	}

	@Override // 회원가입
	public void input() throws Exception{
		
		GambleDAO dao = new GambleDAO();
		
		int sum;
		String id; //아이디 확인용
		String jumin; //주민번호 확인용
		String pwd1; //1차 비밀번호 확인용
		String pwd2; //2차 비밀번호 확인용 
		String pat2 = "[\\w]+-[\\w]+-[\\w]+"; //전화번호 저장 방법 검사
		char ch;
		
			GambleException ge = new GambleException();
						
			for(int i = 0; i < 100; ++i){
				
				System.out.println();
				
			}
			
			for(int i=0;i<hi.horseid.length;i++){
				System.out.println(hi.horseid[i]);

				try {
					sleep(10);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			System.out.println("--------------------------");
			System.out.println("회원가입");
			
			GambleDTO vo = new GambleDTO();
			
			int s = 0;
						
			do{
				try {
										
					System.out.print("ID(영문 + 숫자 5~10자리)를 입력하세요 ▶ ");
					id = sc.next();
					
					ge.inputIdcheck(id);
					
					id = dao.selectId(id);
					
					if(id==null){
						System.out.println("중복된 아이디가 있습니다!!");
						System.out.print("다른 ID로 가입하시겠습니까?[Y/N] ▶ ");

						do{

							ch = (char) System.in.read();
							System.in.skip(2);

						}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');

						if(ch == 'n' || ch =='N'){
							return;
							
						}if(ch =='y' || ch =='Y'){
							id = null;
						}
					}
	
				} catch (Exception e) {
					
					System.out.println(e.toString());
					id = null;
					
					if(id==null){
						System.out.print("다른 ID로 가입하시겠습니까?[Y/N] ▶ ");

						do{

							ch = (char) System.in.read();
							System.in.skip(2);

						}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');

						if(ch == 'n' || ch =='N'){
							return;
							
						}if(ch =='y' || ch =='Y'){
							id = null;
						}
					}
					
				}
								
			}while(id==null);
			
			vo.setId(id);
					
			System.out.println("password 재입력시 동일해야합니다");
					
			System.out.print("password ▶ ");
					
			vo.setPwd(sc.next());

			pwd1 = vo.getPwd(); 
					
			do{
						
				try {

					System.out.print("password 재입력 ▶ ");
					pwd2 = sc.next(); 

					s=1;

					if(!pwd1.equals(pwd2)){
						s =0;
					}
					
					ge.inputPwCheck(s);
					
				} catch (Exception e) {
					
					System.out.println(e.toString());
					
				}

			}while(s<=0);
				
			System.out.print("이름 ▶ ");
			vo.setName(sc.next());
			
			try {
				
				do{
					try {
						
						System.out.print("주민번호 [xxxxxx-xxxxxxx] ▶ ");
						jumin = sc.next();
						
						jumin = dao.selectJumin(jumin);
						
						ge.inputJaCheck(jumin);
						
						vo.setJumin(jumin);
						
					} catch (Exception e) {
						System.out.println(e);
					}
					
				}while(vo.getJumin().length()!=14);
				
				if(vo.getJumin().substring(0,1).equals("0") || vo.getJumin().substring(0,1).equals("1") || vo.getJumin().substring(0,1).equals("2")){
					
					sum = 2000 + Integer.parseInt(vo.getJumin().substring(0,1))*10 + Integer.parseInt(vo.getJumin().substring(1,2));
					
				}else{
					
					sum = 1900 + Integer.parseInt(vo.getJumin().substring(0,1))*10 + Integer.parseInt(vo.getJumin().substring(1,2));

				}				
				
				ge.inputJuCheck(sum);
				
			} catch (Exception e) {
								
				for(int i=0;i<hi.airplane.length;i++){
					
					System.out.println(hi.airplane[i]);

					try {

						sleep(100);

					} catch (Exception e1) {
												
					}
										
				}
				
				System.exit(0);
				
			}
			
			do{//전화번호 입력
				
				System.out.print("전화번호 입력[010-XXXX-XXXX] : ");
				vo.setTel(br.readLine());
				
				if(!Pattern.matches(pat2, vo.getTel())){
					System.out.println("잘못입력하셨습니다!!");
					vo.setTel(null);
				}
					
			}while(vo.getTel()==null);
			
			int result = dao.insertData(vo);
			
			if(result!=0){
				System.out.println("회원가입 성공!!");
			}else{
				System.out.println("회원가입 실패!!");
			}
	
			System.in.skip(50);
			
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			for(int i = 0; i < 100; ++i){ //공간생성

				System.out.println();

			}
			
	}

	@Override //회원검색
	public void search(){
		
		String searchId;
		String searchJumin;
		
		System.out.println("--------------------------");
		System.out.println("회원찾기");

		System.out.print("ID를 입력하세요 ▶ ");
		searchId = sc.next();
			
		System.out.print("주민번호를 입력하세요 ▶ ");
		searchJumin = sc.next();
				
		GambleDTO dto = dao.getList(searchId, searchJumin);	
		
		System.out.println();
		
		if(dto!=null){
			System.out.println("아이디 : " + dto.getId());
			System.out.println("비밀번호 : " + dto.getPwd());
		}else{
			System.out.println("찾으시는 회원이 없습니다!!");
		}
		
		try {
			sleep(1000);
		} catch (Exception e) {}
			
		for(int i = 0; i < 100; ++i){ //공간생성

			System.out.println();

		}
		
	}

}
