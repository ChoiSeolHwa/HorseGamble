package com.horseDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HorLogin extends Thread {

	GambleDTO dto=new GambleDTO();
	
	public void Hor(String id) throws IOException {
		
		HorLogin hl=new HorLogin();
		HorseStart hs=new HorseStart();
		Bang gb = new Bang();
		HorseImage hi = new HorseImage();
		
		char in;
		
		while(true){
			
			for(int i=0;i<hi.story.length;i++){
				System.out.println(hi.story[i]);

				try {
					sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			System.out.println("**************************************************************************");
			
			do{
				
				System.out.print("1.�����ϱ� 2.�������� 3.���� 4.ȸ���������� 5.ȸ��Ż�� 6.����  ");
				System.out.print("�Է� �� ");
				in=(char)System.in.read();
				
				System.in.skip(50);
				
				if(in<'1'||in>'6'){
					System.out.println("�߸��Է��ϼ̽��ϴ� !! ");
				}
			}while(in<'1'||in>'6');
			
			switch (in){
			
			case '1':
				hs.run(id); break;
			case '2':
				hl.story(id);break;
			case '3':
				gb.bangMain(id); break;
			case '4':
				hl.update(id); break;
			case '5':
				hl.delete(id); return;
			default:
				return;
				 
			}
			
			for(int i = 0; i < 50; ++i){ //��������

				System.out.println();

			}
		}
	}
	
	//ȸ����������
	public void update(String id){
		Scanner sc=new Scanner(System.in);
		try {

			HorLogin hl=new HorLogin();
			
			dto.setId(id);
			System.out.print("������ �н�����? �� ");
			dto.setPwd(sc.next());
			System.out.print("������ ��ȭ��ȣ? �� ");
			dto.setTel(sc.next());
			
			int result=hl.updateData(dto);
			
			if(result!=0){
				System.out.println("��������");
				return;	
			}else{
				System.out.println("�������� ");
				return;
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		try {
			
			sleep(1000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	//ȸ����������(dao)
	public int updateData(GambleDTO dto){
		int result=0;
		
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql = "update horgi set pwd=? ,tel=? ";
			sql+= "where id=? ";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getId());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	return result;	
	}
	
	//ȸ��Ż��
	public void delete(String id){
		
		Scanner sc = new Scanner(System.in);
		HorLogin hl=new HorLogin();
		
		char ch;
		String pwd = null;
	
		
		try {

			do{
				
				System.out.println("ȸ��Ż�� �Ͻðڽ��ϱ�?[y/n] ");			
				ch=(char) System.in.read();
				System.in.skip(50);
				
			}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');

			if(ch == 'y' || ch =='Y'){
				
				System.out.print("��й�ȣ�� �Է����ּ���");
				pwd = sc.next();				
				
				System.out.println(pwd);
				
				int result = hl.deleteData(id,pwd);
				
				if(result !=0){
					System.out.println("Ż�𼺰�! ");
					System.exit(0);
				}else{
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�!!!!!!");
				}

			}
			
			try {
				
				sleep(1000);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	
		sc.close();
	}
	
	
	//ȸ��Ż��(dao)
	public int deleteData(String id,String pwd){
		
		int result=0;
		
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		String sql;
	
		try {
			sql="delete horgi where id = ? and pwd = ?";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	//��������
	public void story(String id){
		
		int n=0;
		
		HorLogin hl=new HorLogin();
		
		List<GambleDTO> lists = hl.storyData(id);
		
		Iterator<GambleDTO> it = lists.iterator();
		
		while(it.hasNext()){
			
			GambleDTO dto = it.next();
			if(dto.getId()!=null){
				
				System.out.println();
				System.out.println("========================");
				System.out.printf(" �� ���̵� : %s \n",dto.getId());
				System.out.printf(" �� ����ȣ : %s�� \n",dto.getSun());
				System.out.printf(" �� ���� : %s�� \n",dto.getRank());
				System.out.println("========================");

				n++;
			}
			
			try {
				
				sleep(1000);
				
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		
		if(n==0){
			HorseImage hi = new HorseImage();
			for(int i=0;i<hi.sadface.length;i++){
				System.out.println(hi.sadface[i]);

				try {
					sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		
		}
		
		try {
			
			sleep(1000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//��������(dao)
	public List<GambleDTO> storyData(String id){
		
		List<GambleDTO> lists = new ArrayList<GambleDTO>();
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql;
		
		try {
			sql = "select id,sun,rank from horcnt ";
			sql+= "where id=? ";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				GambleDTO dto = new GambleDTO(); 
				
				dto.setId(rs.getString("id"));
				dto.setSun(rs.getString("sun"));
				dto.setRank(rs.getString("rank"));
				
				lists.add(dto);

			}
			
			pstmt.close();
			rs.close();			
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return lists;

	}
}
