package com.ssafy.dto;

import java.io.Serializable;

public class Qna implements Serializable{
	private int no;
	private String question;
	private String userid;
	private String answer;
	private String adminid;
	private int hit;
	private String registerTime;
	private String answerTime;
	private String status;
	
	public Qna() {
		super();
	}

	public Qna(int no, String question, String userid, String answer, String adminid, int hit, String registerTime,
			String answerTime, String status) {
		super();
		this.no = no;
		this.question = question;
		this.userid = userid;
		this.answer = answer;
		this.adminid = adminid;
		this.hit = hit;
		this.registerTime = registerTime;
		this.answerTime = answerTime;
		this.status = status;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
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

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Qna [no=" + no + ", question=" + question + ", userid=" + userid + ", answer=" + answer + ", adminid="
				+ adminid + ", hit=" + hit + ", registerTime=" + registerTime + ", answerTime=" + answerTime
				+ ", status=" + status + "]";
	}
	
	
	
	
}
