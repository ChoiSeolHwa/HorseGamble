package com.horseDB;

public class BangDTO {
	
	private String id;
	private String title;
	private String content;
	private int no;
	private String nal;
	
	
	
	public String getNal() {
		return nal;
	}

	public void setNal(String nal) {
		this.nal = nal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		
		String str;
		
		str = String.format("�� ��ȣ: %1d \n�� I D : %2s \n�� ��¥: %s \n�� ����: %1s \n�� ����: %1s \n",no,id,nal,title,content);
		
		return str;
		
	}

}
