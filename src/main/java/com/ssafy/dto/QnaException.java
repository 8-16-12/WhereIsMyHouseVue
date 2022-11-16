package com.ssafy.dto;


public class QnaException extends RuntimeException {
	public QnaException() {
		super("Book 정보를 처리 중 오류 발생");
	}
	public QnaException(String msg) {
		super(msg);
	}
}
