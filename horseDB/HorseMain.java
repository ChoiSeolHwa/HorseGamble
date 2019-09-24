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

			System.out.println("**********************************�������ּ���**********************************");

			do{
				System.out.print("�Է� �� ");
				in = (char) System.in.read();
				
				System.in.skip(50);
				
				if(in<'0'||in>'4'){
					System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
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
				for(int i = 0; i < 50; ++i){ //��������

					System.out.println();

				}
				for(int i=0;i<hi.ending.length;i++){	//����ũ����
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
	
	//������ �α���
	public void adminlogin(){
		
		HorseMain hm = new HorseMain();
		
		Scanner sc = new Scanner(System.in);
		
		String id = null;
		String pwd = null;
		
		try {
			
			System.out.print("������ ���̵� : ");
			id = sc.next();
			
			System.out.print("������ ��й�ȣ : ");
			pwd = sc.next();
			
			GambleDTO dto = hm.adminLogin(id, pwd);
			
			if(dto!=null){

				hm.adminMain(id);

			}else{
				
				System.out.println("�α��� �����ϼ̽��ϴ�!!");
				return;
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		
	}
	
	//������ �α���(dao)
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
	
	//������ ����
	public void adminMain(String id) throws IOException{
		Connection conn = DBhor.getconConnection();
		Scanner sc=new Scanner(System.in);
		HorseMain hm = new HorseMain();
		
		int n=0;
		
		for(int i = 0; i < 20; ++i){ //��������

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

		System.out.println("*******************************�����ڿ����*******************************");
		System.out.println("*******************************�������ּ���*******************************");

		char in; 
		
		do{
			System.out.println("1.ȸ���˻� 2.�������� 3.������� 4.���ϻ��� 5.������ ");
			System.out.print("�Է� �� ");
			in = (char)System.in.read();
			
			System.in.skip(50);
			
			if(in<'1'||in>'5'){
				System.out.println("�߸� �Է��ϼ̽��ϴ�!!");
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
	
	//ȸ������� ����Ż��
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
			System.out.printf("����̵�: %s \n���н�����: %s \n���̸�: %s \n���ֹι�ȣ: %s \n����ȭ��ȣ: %s\n",dto.getId(),dto.getPwd(),dto.getName(),dto.getJumin(),dto.getTel());
			System.out.println("==========================");
			
		}
		
		try {
			do{
				
			System.out.println("\n1.ȸ�� Ż���Ű�� 2.�ڷΰ���");
			ch=(char)System.in.read();
			
			System.in.skip(50);
			
			if(ch<'1'||ch>'2'){
				System.out.println("�߸� �Է��ϼ̽��ϴ� !!");
			}
			
			}while(ch<'1'||ch>'2');

			if(ch=='1'){
				System.out.println("Ż���ų ȸ���� ���̵� �Է����ּ���");
				id=sc.next();
				
				
				do{
				System.out.println("�ش� ȸ���� Ż���Ű�ڽ��ϱ�[y/n]?");
				ch=(char) System.in.read();
				System.in.skip(50);
				
			}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');
				
				if(ch == 'y' || ch =='Y'){
					
					int result=hm.adminTalData(id);
					
					if(result!=0){
						System.out.println("Ż��Ǿ����ϴ�!!");
						
						try {
							sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}else{
						System.out.println("Ż�� ����!!!!!!");
					}
				}
				
			}
			
			hm.adminMain(id);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//ȸ�� ���(�����ڿ�dao)
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
	
	//ȸ�� Ż��(�����ڿ�dao)
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
	
	//�������� ����
	public void insertAdminBang(String id){
		
		HorseMain hm = new HorseMain();
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println();
			
			System.out.print("���� �Է� : ");
			String title = br.readLine();
			
			System.out.print("�����Է� : ");
			String content = br.readLine();
			
			int result = hm.insertAdminBang(id,title,content);
			
			if(result!=0){
				System.out.println("�������� �Է� �Ϸ�!!");
			}else{
				System.out.println("�������� �Է� ����!!");
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
	//���� ����(dao)
	
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
	
	//���� ���
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

	//���� ���(dao)
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
	
	//���� ����
	
	public void deleteAdminBang(String id){
		
		HorseMain hm = new HorseMain();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			
			System.out.println();
			System.out.print("������ ���� ��ȣ : ");
			
			String dn = br.readLine();
			
			int result = hm.deleteAdminBang2(dn);
			
			if(result!=0){
				System.out.println("���� ����!!");
			}else{
				System.out.println("���� �Խñ��Դϴ�!!");
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
		
	//���� ����(dao)
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
