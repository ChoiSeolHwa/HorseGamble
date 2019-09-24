package com.horseDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Bang extends Thread{

	Scanner sc = new Scanner(System.in);
	BangDAO dao = new BangDAO();
	HorseImage hi = new HorseImage();

	public void bangMain(String id) throws IOException{
		
	    char in;
	    Bang gb = new Bang();
	    
		while(true){
			
			for(int i = 0; i < 20; ++i){ //��������

				System.out.println();

			}
			
			for(int i=0;i<hi.bang.length;i++){
				System.out.println(hi.bang[i]);

				try {
					sleep(30);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
	    
			System.out.println("******************************************************");
		
			do{
				
				System.out.print("1.���� �ۼ� 2.���� ��� 3.���� ���� 4.����  ");
				System.out.print("�Է� �� ");
				in=(char)System.in.read();
				System.out.println();
				System.in.skip(50);
				
				if(in<'1'||in>'4'){
					System.out.println("�߸��Է��ϼ̽��ϴ� !! ");
				}
			}while(in<'1'||in>'4');
			
			switch (in){
			
			case '1':
				gb.insertBang(id); break;
			case '2':
				gb.printBang(); break;
			case '3':
				gb.deleteBang(id); break;
			default:
				return;
				 
			}
			
			for(int i = 0; i < 30; ++i){ //��������

				System.out.println();

			}
			
		}
			
	}
	
	//���� ����,���� �Է�
	public void insertBang(String id) {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println();

			System.out.print("���� �Է� : ");
			String title = br.readLine();


			System.out.print("���� �Է� : ");
			String content = br.readLine();

			int result = dao.insertBang(id,title,content);

			if(result!=0){
				System.out.println("�Է� ����!!");
			}else{
				System.out.println("�Է� ����!!");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	// ���� �������
	public void printBang(){

		List<BangDTO> lists = dao.getList();

		Iterator<BangDTO> it = lists.iterator();

		while(it.hasNext()){

			BangDTO dto = it.next();
			System.out.println();
			System.out.println("==========================================================================");
			System.out.println(dto.toString());
			System.out.println("==========================================================================");

			try {
				
				sleep(1000);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

	}

	//���� ����
	public void deleteBang(String id){

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println();
			System.out.print("������ ���� ��ȣ : ");
			
			String dt = br.readLine();
			
			int result = dao.deleteBang(dt,id);
			 
			
			
			
			
			if(result!=0){
				System.out.println("���� ����!!");
			}else{
				System.out.println("������ �� �۸� �����Ҽ��ֽ��ϴ�!");
				
			}
			
			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}