package com.horseDB;

import java.util.Scanner;

class HorseStart extends Thread{
	
	Scanner sc=new Scanner(System.in);
	GambleDTO hv= new GambleDTO();
	GambleImpl gi=new GambleImpl();

	public void run(String id) {
		
		HorseImage hi=new HorseImage();
		
		for(int i=0;i<hi.horseIm.length;i++){
			System.out.println(hi.horseIm[i]);

			try {
				sleep(40);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		String [] start={"             게","   임","   을","   시","   작","   하","   겠","   습","   니","   다","!!!"};

		for(int i=0;i<start.length;i++){
			System.out.print(start[i]);

			try {

				sleep(100);

			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

			char sun='0';
		
			do{
				
				try {
					
					char ch;
					System.out.println();
					System.out.println();
					System.out.println("\n말을 선택하여 주십시오!");
					System.out.println();
					System.out.println("┌─────────┐    ┌─────────┐    ┌─────────┐");
					System.out.println("│1번 기수 : 박창진 │    │2번 기수 : 박진수 │    │3번 기수 : 최설화 │");
					System.out.println("└────────━┘    └────────━┘    └────────━┘");
					System.out.println("           ┌─────────┐      ┌─────────┐");
					System.out.println("           │4번 기수 : 나현우 │      │5번 기수 : 최보근 │");
					System.out.println("           └────────━┘      └────────━┘");
					System.out.println();
					System.out.print("말 선택 ▶ ");
					sun = (char)System.in.read();
					System.in.skip(50);

					if(sun=='1' || sun=='2' || sun =='3' || sun=='4' || sun=='5'){
						System.out.println(sun + "번 말을 선택하셨습니다.");

						do{

							System.out.print(sun + "번 말로 진행하시겠습니까?[y/n]");
							ch = (char)System.in.read();
							System.in.skip(50);

						}while(ch != 'y' && ch !='Y' && ch != 'n' && ch != 'N');

						if(ch == 'n' || ch =='N'){

							sun = '0';

						}

					}else{
						
						System.out.println("잘못 입력하셨습니다!!");
						System.out.println("말의 번호는 1~5번까지 입니다!!");
						
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}

			}while(sun!='1' && sun!='2' && sun !='3' && sun!='4' && sun!='5');
			
			Runhs run=new Runhs();
			
			System.out.println();
			
			run.run(id,sun);
						
	}

}
	

