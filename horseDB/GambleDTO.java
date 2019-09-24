package com.horseDB;

public class GambleDTO{

	private String id;	//아이디
	private String pwd;	//비밀번호
	private String name;	//이름
	private String jumin;	//주민번호
	private String tel;	//전화번호
	private String sun;	//선택 말(전적확인용)
	private String rank; //말 등수(전적확인용)

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJumin() {
		return jumin;
	}

	public void setJumin(String jumin) {
		this.jumin = jumin;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
		public String toString(){
		
			String str =null;
			
			str = String.format("아이디 : %s, 비밀번호 : %s\n이름 : %s, 주민번호 : %s, 전화번호 : %s\n"
				,getId(),getPwd(),getName(),getJumin(),getTel());
	
			return str;
	}
	
}