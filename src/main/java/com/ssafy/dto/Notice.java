package com.ssafy.dto;

import java.io.Serializable;

public class Notice implements Serializable{
	private int no;
	private String id;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;

	public Notice() {
		super();
	}

	public Notice(int no, String id, String subject, String content, int hit, String registerTime) {
		this.no = no;
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "Notice [no=" + no + ", id=" + id + ", subject=" + subject + ", content=" + content + ", hit=" + hit
				+ ", registerTime=" + registerTime + "]";
	}

	
	
}
