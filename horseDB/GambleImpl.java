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
	
	String id = null; //id Ȯ�ο�

	@Override //�α���
	public void login(){
		
		HorLogin hl = new HorLogin();
	
		String pwd = null;; //pwd Ȯ�ο�
		
		try {
		
			System.out.print("���̵� : ");
			id = sc.next();
			
			System.out.print("��й�ȣ : ");
			pwd = sc.next();
			
			GambleDTO dto = dao.login(id,pwd);
			
			if(dto!=null){
				
				for(int i = 0; i < 20; ++i){ //��������
					System.out.println();
				}
				System.out.println("		            \\\\\\///             ");
				System.out.println("		           / _  _ \\            ");
				System.out.println("		         (| (.)(.) |)          ");
				System.out.println("		.------.OOOo--()--oOOO.------. ");
				System.out.println("		|                            | ");
				System.out.println("		" + id + "   ���� �����ϼ̽��ϴ�!!   ");
				System.out.println("		|                            | ");
				System.out.println("		'------.oooO-----------------' ");
				System.out.println("		        (   )   Oooo.          ");
				System.out.println(" 		        \\ (    (   )          ");
				System.out.println(" 		         \\_)    ) /           ");
				System.out.println("   			       (_/            ");
			
				
				for(int i = 0; i < 15; ++i){ //��������
					System.out.println();
				}
				
				try {
					sleep(2000);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				
				hl.Hor(id);
			
			}else{
				System.out.println("�α��� �����ϼ̽��ϴ�!!");
				return;
			}
		} catch (Exception e) {
		System.out.println(e.toString());
		}

	}

	@Override // ȸ������
	public void input() throws Exception{
		
		GambleDAO dao = new GambleDAO();
		
		int sum;
		String id; //���̵� Ȯ�ο�
		String jumin; //�ֹι�ȣ Ȯ�ο�
		String pwd1; //1�� ��й�ȣ Ȯ�ο�
		String pwd2; //2�� ��й�ȣ Ȯ�ο� 
		String pat2 = "[\\w]+-[\\w]+-[\\w]+"; //��ȭ��ȣ ���� ��� �˻�
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
			System.out.println("ȸ������");
			
			GambleDTO vo = new GambleDTO();
			
			int s = 0;
						
			do{
				try {
										
					System.out.print("ID(���� + ���� 5~10�ڸ�)�� �Է��ϼ��� �� ");
					id = sc.next();
					
					ge.inputIdcheck(id);
					
					id = dao.selectId(id);
					
					if(id==null){
						System.out.println("�ߺ��� ���̵� �ֽ��ϴ�!!");
						System.out.print("�ٸ� ID�� �����Ͻðڽ��ϱ�?[Y/N] �� ");

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
						System.out.print("�ٸ� ID�� �����Ͻðڽ��ϱ�?[Y/N] �� ");

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
					
			System.out.println("password ���Է½� �����ؾ��մϴ�");
					
			System.out.print("password �� ");
					
			vo.setPwd(sc.next());

			pwd1 = vo.getPwd(); 
					
			do{
						
				try {

					System.out.print("password ���Է� �� ");
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
				
			System.out.print("�̸� �� ");
			vo.setName(sc.next());
			
			try {
				
				do{
					try {
						
						System.out.print("�ֹι�ȣ [xxxxxx-xxxxxxx] �� ");
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
			
			do{//��ȭ��ȣ �Է�
				
				System.out.print("��ȭ��ȣ �Է�[010-XXXX-XXXX] : ");
				vo.setTel(br.readLine());
				
				if(!Pattern.matches(pat2, vo.getTel())){
					System.out.println("�߸��Է��ϼ̽��ϴ�!!");
					vo.setTel(null);
				}
					
			}while(vo.getTel()==null);
			
			int result = dao.insertData(vo);
			
			if(result!=0){
				System.out.println("ȸ������ ����!!");
			}else{
				System.out.println("ȸ������ ����!!");
			}
	
			System.in.skip(50);
			
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			for(int i = 0; i < 100; ++i){ //��������

				System.out.println();

			}
			
	}

	@Override //ȸ���˻�
	public void search(){
		
		String searchId;
		String searchJumin;
		
		System.out.println("--------------------------");
		System.out.println("ȸ��ã��");

		System.out.print("ID�� �Է��ϼ��� �� ");
		searchId = sc.next();
			
		System.out.print("�ֹι�ȣ�� �Է��ϼ��� �� ");
		searchJumin = sc.next();
				
		GambleDTO dto = dao.getList(searchId, searchJumin);	
		
		System.out.println();
		
		if(dto!=null){
			System.out.println("���̵� : " + dto.getId());
			System.out.println("��й�ȣ : " + dto.getPwd());
		}else{
			System.out.println("ã���ô� ȸ���� �����ϴ�!!");
		}
		
		try {
			sleep(1000);
		} catch (Exception e) {}
			
		for(int i = 0; i < 100; ++i){ //��������

			System.out.println();

		}
		
	}

}
