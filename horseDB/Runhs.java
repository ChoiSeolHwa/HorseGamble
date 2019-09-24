package com.horseDB;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

class Runhs extends Thread{ 
	
	public void run(String id,char sun){ 
				
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		HorseImage hi = new HorseImage();
		HorseStart hs = new HorseStart();
		String st = Character.toString(sun);
		
		String[][] ball = new String[5][70];    //말판(경주장)
		String horse = "▶";                    //말  
		String[] hna = {"창진","진수","설화","현우","보근"};  //경주 말 이름 
		String[] seq = {"1","2","3","4","5"};  //순위 말판 배열이 문자타입이라  
		int nansu = 0;                       //난수
		int last = 0;                        //최종골인 확인용
		int cnt = 0;                         //말위치 + 난수
		int num = 0;						 //달리는 말의 번호
		char ch = 0;					  	//계속 여부 확인용
		String rank = null;
		int temp_su = Integer.parseInt(st);						//선택말 담기용
		
		for(int i = 0; i < 50; ++i){ //공간생성

			System.out.println();

		}
		
		for(int k=0;k<hi.run1.length;k++){	//최초 대기 그림
			System.out.println(hi.run1[k]);
		
		}try {

			sleep(30);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//경주마 및 경주장을 배열에 준비한다.
		for(int i = 0; i < ball.length; ++i){
			for(int j = 0; j < ball[i].length; ++j){
				ball[i][j] = "_";
			}
			ball[i][1] = horse;
			ball[i][0] = hna[i];
		}

		//배열에 기록된  경주 스타드 내용을 출력한다
		
		for(int i = 0; i < ball.length; ++i){
			for(int j = 0; j < ball[i].length; ++j){
				System.out.printf("%s",ball[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();	
		
		//게임 시작
		System.out.println("                             < Enter  게임  Start >");
		try {
			String aaa = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(last < ball.length) {							//last가 말의 갯수만큼 돌아갈 수 있도록			
			
			//배열에 경주마들을 기록한다
			for(int i = 0; i < ball.length; ++i){			//i를 말의 갯수만큼 반복
				//5마리 골인 지점 통과 체크
				int x = ball[i].length - 1;					//x는 말의 갯수 -1
				
				if(ball[i][x] == horse){ 					//만약 말의 위치가 마지막 위치에 있다면
					ball[i][x] = seq[last] ;				//마지막 위치에 등수 표시
					if(i == temp_su-1){		
						rank = seq[last];	
					}
					++last;									//last ++

				}else{										//말이 마지막 위치에 없다면

					//배열에 각 경주마들의  경주를 기록한다
					for(int j = 0; j < ball[i].length; ++j){	//j값을 말의 갯수만큼 돌아줌
						if(ball[i][j] == horse){				//경기장의 위치에 말이 있을 경우
							nansu = (int)(Math.random() * 2);	//난수를 0.0~1.0 사이를 랜덤으로 입력
							ball[i][j] = "_";					//말이 있던 위치에 경기장 설치
							cnt = j + nansu;					//기존에 있던 말 위치에 nansu로 전진
							if (cnt >= ball[i].length) { 		
								cnt = ball[i].length - 1;	//cnt가 경기장의 길이를 넘어갈 경우 -1
							}
							ball[i][cnt] = horse;			//끝까지 안갔을 경우 말의 위치를 지정해줌
						}
					}//for2
				}
			}//for 1
			

			//시간지연(너무빠름)
			//for(int i = 0; i < 100000000; ++i){ }
			try{
				Thread.sleep(150);  //1000 이면 1초, 100 이면 0.1초 
			}catch(InterruptedException e){}

			//화면clear

			for(int i = 0; i < 100; ++i){ 
				System.out.println();  
			}

			//배열에 기록된 경주 내용을 출력한다
			
			num += 1;
			
			if(num%2==0){
				for(int k=0;k<hi.run1.length;k++){
					System.out.println(hi.run1[k]);
				
				}
			}else{
				for(int k=0;k<hi.run2.length;k++){
					System.out.println(hi.run2[k]);
				
				}
			}
			
			for(int i = 0; i < ball.length; ++i){
				for(int j = 0; j < ball[i].length; ++j){
					System.out.printf("%s",ball[i][j]);
				}
				System.out.println();
			}

		}//while
		System.out.println();
		
		count(id,st,rank);

		//계속 진행 여부 확인
		System.out.print("계속하시겠습니까?[Y/N] : ");
		
		while(true){

			try {
				ch = (char) System.in.read();
				System.in.skip(50);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if(ch == 'n' || ch == 'N'){
				
				for(int i = 0; i < 100; ++i){ //공간생성

					System.out.println();

				}
				
				try {

					sleep(1000);

				} catch (Exception e) {}
				
				for(int i = 0; i < 100; ++i){ //공간생성

					System.out.println();

				}

			}
			
			if(ch =='y' || ch =='Y'){	//계속 진행

				hs.run(id);
				
			}else{
				return;
			}
				
		}
		
	}
	//게임전적 dao
	
	public int count(String id,String st, String rank){
		int result=0;
		Connection conn=DBhor.getconConnection();
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql = "insert into horcnt (id,sun,rank) values (?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
			pstmt.setString(2,st);
			pstmt.setString(3,rank);
			
			result=pstmt.executeUpdate();
			pstmt.close();			
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}
}