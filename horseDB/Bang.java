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
			
			for(int i = 0; i < 20; ++i){ //공간생성

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
				
				System.out.print("1.방명록 작성 2.방명록 출력 3.방명록 삭제 4.이전  ");
				System.out.print("입력 ▶ ");
				in=(char)System.in.read();
				System.out.println();
				System.in.skip(50);
				
				if(in<'1'||in>'4'){
					System.out.println("잘못입력하셨습니다 !! ");
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
			
			for(int i = 0; i < 30; ++i){ //공간생성

				System.out.println();

			}
			
		}
			
	}
	
	//방명록 제목,내용 입력
	public void insertBang(String id) {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			System.out.println();

			System.out.print("제목 입력 : ");
			String title = br.readLine();


			System.out.print("내용 입력 : ");
			String content = br.readLine();

			int result = dao.insertBang(id,title,content);

			if(result!=0){
				System.out.println("입력 성공!!");
			}else{
				System.out.println("입력 실패!!");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	// 방명록 내용출력
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

	//방명록 삭제
	public void deleteBang(String id){

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			System.out.println();
			System.out.print("삭제할 방명록 번호 : ");
			
			String dt = br.readLine();
			
			int result = dao.deleteBang(dt,id);
			 
			
			
			
			
			if(result!=0){
				System.out.println("삭제 성공!!");
			}else{
				System.out.println("본인이 쓴 글만 삭제할수있습니다!");
				
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