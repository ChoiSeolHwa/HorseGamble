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
		
		String[][] ball = new String[5][70];    //����(������)
		String horse = "��";                    //��  
		String[] hna = {"â��","����","��ȭ","����","����"};  //���� �� �̸� 
		String[] seq = {"1","2","3","4","5"};  //���� ���� �迭�� ����Ÿ���̶�  
		int nansu = 0;                       //����
		int last = 0;                        //�������� Ȯ�ο�
		int cnt = 0;                         //����ġ + ����
		int num = 0;						 //�޸��� ���� ��ȣ
		char ch = 0;					  	//��� ���� Ȯ�ο�
		String rank = null;
		int temp_su = Integer.parseInt(st);						//���ø� ����
		
		for(int i = 0; i < 50; ++i){ //��������

			System.out.println();

		}
		
		for(int k=0;k<hi.run1.length;k++){	//���� ��� �׸�
			System.out.println(hi.run1[k]);
		
		}try {

			sleep(30);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//���ָ� �� �������� �迭�� �غ��Ѵ�.
		for(int i = 0; i < ball.length; ++i){
			for(int j = 0; j < ball[i].length; ++j){
				ball[i][j] = "_";
			}
			ball[i][1] = horse;
			ball[i][0] = hna[i];
		}

		//�迭�� ��ϵ�  ���� ��Ÿ�� ������ ����Ѵ�
		
		for(int i = 0; i < ball.length; ++i){
			for(int j = 0; j < ball[i].length; ++j){
				System.out.printf("%s",ball[i][j]);
			}
			System.out.println();
		}
		
		System.out.println();	
		
		//���� ����
		System.out.println("                             < Enter  ����  Start >");
		try {
			String aaa = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(last < ball.length) {							//last�� ���� ������ŭ ���ư� �� �ֵ���			
			
			//�迭�� ���ָ����� ����Ѵ�
			for(int i = 0; i < ball.length; ++i){			//i�� ���� ������ŭ �ݺ�
				//5���� ���� ���� ��� üũ
				int x = ball[i].length - 1;					//x�� ���� ���� -1
				
				if(ball[i][x] == horse){ 					//���� ���� ��ġ�� ������ ��ġ�� �ִٸ�
					ball[i][x] = seq[last] ;				//������ ��ġ�� ��� ǥ��
					if(i == temp_su-1){		
						rank = seq[last];	
					}
					++last;									//last ++

				}else{										//���� ������ ��ġ�� ���ٸ�

					//�迭�� �� ���ָ�����  ���ָ� ����Ѵ�
					for(int j = 0; j < ball[i].length; ++j){	//j���� ���� ������ŭ ������
						if(ball[i][j] == horse){				//������� ��ġ�� ���� ���� ���
							nansu = (int)(Math.random() * 2);	//������ 0.0~1.0 ���̸� �������� �Է�
							ball[i][j] = "_";					//���� �ִ� ��ġ�� ����� ��ġ
							cnt = j + nansu;					//������ �ִ� �� ��ġ�� nansu�� ����
							if (cnt >= ball[i].length) { 		
								cnt = ball[i].length - 1;	//cnt�� ������� ���̸� �Ѿ ��� -1
							}
							ball[i][cnt] = horse;			//������ �Ȱ��� ��� ���� ��ġ�� ��������
						}
					}//for2
				}
			}//for 1
			

			//�ð�����(�ʹ�����)
			//for(int i = 0; i < 100000000; ++i){ }
			try{
				Thread.sleep(150);  //1000 �̸� 1��, 100 �̸� 0.1�� 
			}catch(InterruptedException e){}

			//ȭ��clear

			for(int i = 0; i < 100; ++i){ 
				System.out.println();  
			}

			//�迭�� ��ϵ� ���� ������ ����Ѵ�
			
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

		//��� ���� ���� Ȯ��
		System.out.print("����Ͻðڽ��ϱ�?[Y/N] : ");
		
		while(true){

			try {
				ch = (char) System.in.read();
				System.in.skip(50);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			if(ch == 'n' || ch == 'N'){
				
				for(int i = 0; i < 100; ++i){ //��������

					System.out.println();

				}
				
				try {

					sleep(1000);

				} catch (Exception e) {}
				
				for(int i = 0; i < 100; ++i){ //��������

					System.out.println();

				}

			}
			
			if(ch =='y' || ch =='Y'){	//��� ����

				hs.run(id);
				
			}else{
				return;
			}
				
		}
		
	}
	//�������� dao
	
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