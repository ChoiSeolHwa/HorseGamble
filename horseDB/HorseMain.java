package com.horseDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HorseMain extends Thread{

	public static void main(String[] args) throws Exception {
		
		Connection conn = DBhor.getconConnection();
		HorseImage hi = new HorseImage();
		Gamble ob =  new GambleImpl();
		Scanner sc = new Scanner(System.in);
		HorseMain hm = new HorseMain();
		
		while(true){

			for(int i=0;i<hi.hormain.length;i++){
				System.out.println(hi.hormain[i]);

				try {
					sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			char in;

			System.out.println("**********************************선택해주세요**********************************");

			do{
				System.out.print("입력 ▶ ");
				in = (char) System.in.read();
				
				System.in.skip(50);
				
				if(in<'0'||in>'4'){
					System.out.println("잘못 입력하셨습니다!!");
				}

			}while(in<'0'||in>'4');
			
			switch (in) {  
			case '0':
				hm.adminlogin(); break;
			case '1':
				ob.login();break;
			case '2':
				ob.input(); break;
			case '3':
				ob.search(); break;
			default: 
				for(int i = 0; i < 50; ++i){ //공간생성

					System.out.println();

				}
				for(int i=0;i<hi.ending.length;i++){	//엔딩크레딧
					System.out.println(hi.ending[i]);

					try {
						sleep(30);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				DBhor.close();
				System.exit(0);
				conn.close();
				sc.close();
			}
		}
		
	}
	
	//관리자 로그인
	public void adminlogin(){
		
		HorseMain hm = new HorseMain();
		
		Scanner sc = new Scanner(System.in);
		
		String id = null;
		String pwd = null;
		
		try {
			
			System.out.print("관리자 아이디 : ");
			id = sc.next();
			
			System.out.print("관리자 비밀번호 : ");
			pwd = sc.next();
			
			GambleDTO dto = hm.adminLogin(id, pwd);
			
			if(dto!=null){

				hm.adminMain(id);

			}else{
				
				System.out.println("로그인 실패하셨습니다!!");
				return;
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
	
	//관리자 로그인(dao)
	public GambleDTO adminLogin(String id,String pwd){
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GambleDTO dto = null;
		String sql;
		
		try {
			
			sql = "select id,pwd from horgi where id like 'admin' and pwd like '111'";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new GambleDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				
				if(!dto.getId().equals(id)){
					dto = null;
				}
				
				if(!dto.getPwd().equals(pwd)){
					dto = null;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
		
	}
	
	//관리자 메인
	public void adminMain(String id) throws IOException{
		Connection conn = DBhor.getconConnection();
		Scanner sc=new Scanner(System.in);
		HorseMain hm = new HorseMain();
		
		int n=0;
		
		for(int i = 0; i < 20; ++i){ //공간생성

			System.out.println();

		}
		if(n==0){
			HorseImage hi = new HorseImage();
			for(int i=0;i<hi.admin.length;i++){
				System.out.println(hi.admin[i]);

				try {
					sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			
		}

		System.out.println("*******************************관리자용메인*******************************");
		System.out.println("*******************************선택해주세요*******************************");

		char in; 
		
		do{
			System.out.println("1.회원검색 2.공지사항 3.방명록출력 4.방명록삭제 5.나가기 ");
			System.out.print("입력 ▶ ");
			in = (char)System.in.read();
			
			System.in.skip(50);
			
			if(in<'1'||in>'5'){
				System.out.println("잘못 입력하셨습니다!!");
			}

		}while(in<'1'||in>'5');
		
		switch(in){
		
		case '1':
			hm.adminTal(id);break;
		case '2':
			hm.insertAdminBang(id);break;
		case '3':
			hm.printBang(id);break;
		case '4':
			hm.deleteAdminBang(id);break;
		default:
			return;	
		
		}
	}
	
	//회원출력후 강제탈퇴
	public void adminTal(String id){
		
		Scanner  sc= new Scanner(System.in);
		HorseMain hm=new HorseMain();
		List<GambleDTO>lists=hm.adminSelData(id);
		Iterator<GambleDTO>it=lists.iterator();
		
		char ch;
		
		while(it.hasNext()){
			GambleDTO dto=it.next();
			
			System.out.println();
			System.out.println("==========================");
			System.out.printf("■아이디: %s \n■패스워드: %s \n■이름: %s \n■주민번호: %s \n■전화번호: %s\n",dto.getId(),dto.getPwd(),dto.getName(),dto.getJumin(),dto.getTel());
			System.out.println("==========================");
			
		}
		
		try {
			do{
				
			System.out.println("\n1.회원 탈퇴시키기 2.뒤로가기");
			ch=(char)System.in.read();
			
			System.in.skip(50);
			
			if(ch<'1'||ch>'2'){
				System.out.println("잘못 입력하셨습니다 !!");
			}
			
			}while(ch<'1'||ch>'2');

			if(ch=='1'){
				System.out.println("탈퇴시킬 회원의 아이디를 입력해주세요");
				id=sc.next();
				
				
				do{
				System.out.println("해당 회원을 탈퇴시키겠습니까[y/n]?");
				ch=(char) System.in.read();
				System.in.skip(50);
				
			}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');
				
				if(ch == 'y' || ch =='Y'){
					
					int result=hm.adminTalData(id);
					
					if(result!=0){
						System.out.println("탈퇴되었습니다!!");
						
						try {
							sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}else{
						System.out.println("탈퇴 실패!!!!!!");
					}
				}
				
			}
			
			hm.adminMain(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//회원 출력(관리자용dao)
	public List<GambleDTO> adminSelData(String id){
		
		List<GambleDTO> lists=new ArrayList<GambleDTO>();
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql;
		
		try {
			
			sql="select id,pwd,name,jumin,tel from horgi ";
			
			pstmt=conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				
				GambleDTO dto=new GambleDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setJumin(rs.getString("jumin"));
				dto.setTel(rs.getString("tel"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;
	
	}
	
	//회원 탈퇴(관리자용dao)
	public int adminTalData(String id){
		
		int result=0;
		
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql="delete horgi where id=? ";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
		
	}
	
	//공지사항 삽입
	public void insertAdminBang(String id){
		
		HorseMain hm = new HorseMain();
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println();
			
			System.out.print("제목 입력 : ");
			String title = br.readLine();
			
			System.out.print("내용입력 : ");
			String content = br.readLine();
			
			int result = hm.insertAdminBang(id,title,content);
			
			if(result!=0){
				System.out.println("공지사항 입력 완료!!");
			}else{
				System.out.println("공지사항 입력 실패!!");
			}

			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			hm.adminMain(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	//방명록 삽입(dao)
	
	public int insertAdminBang(String id, String title,String content){
		
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
			// TODO: handle exception
		}
		
		return result;
		
	}
	
	//방명록 출력
	public void printBang(String id) throws IOException{
		
		HorseMain hm = new HorseMain();
		
		List<BangDTO> lists = hm.printlist(id);
		
		Iterator<BangDTO> it = lists.iterator();
		
		while(it.hasNext()){
			
			BangDTO dto = it.next();
			System.out.println();
			System.out.println("==========================================================================");
			System.out.print(dto.toString());
			System.out.println("==========================================================================");
			
			try {
				sleep(1500);
			} catch (Exception e) {
				// TODO: handle exception
			}
				
		}
		
		hm.adminMain(id);
		
	}

	//방명록 출력(dao)
	public List<BangDTO> printlist(String id){
		
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
			// TODO: handle exception
		}
		
		return lists;
		
	}
	
	//방명록 삭제
	
	public void deleteAdminBang(String id){
		
		HorseMain hm = new HorseMain();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println();
			System.out.print("삭제할 방명록 번호 : ");
			
			String dn = br.readLine();
			
			int result = hm.deleteAdminBang2(dn);
			
			if(result!=0){
				System.out.println("삭제 성공!!");
			}else{
				System.out.println("없는 게시글입니다!!");
			}	
			
			try {
				
				sleep(1000);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			hm.adminMain(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
	}
		
	//방명록 삭제(dao)
	public int deleteAdminBang2(String no){
		
		int result = 0;
		
		Connection conn = DBhor.getconConnection();
		PreparedStatement pstmt = null;
		
		String sql;
		
		try {
			
			sql = "delete bang where no like ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, no);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
		
	}

}
