package com.horseDB;

public class GambleDTO{

	private String id;	//���̵�
	private String pwd;	//��й�ȣ
	private String name;	//�̸�
	private String jumin;	//�ֹι�ȣ
	private String tel;	//��ȭ��ȣ
	private String sun;	//���� ��(����Ȯ�ο�)
	private String rank; //�� ���(����Ȯ�ο�)

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
			
			str = String.format("���̵� : %s, ��й�ȣ : %s\n�̸� : %s, �ֹι�ȣ : %s, ��ȭ��ȣ : %s\n"
				,getId(),getPwd(),getName(),getJumin(),getTel());
	
			return str;
	}
	
}