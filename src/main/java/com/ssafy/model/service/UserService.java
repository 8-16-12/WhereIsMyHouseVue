package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.dto.User;

public interface UserService {
	
	void insert(User user);						// 회원가입
	void update(User user);						// 회원 정보 수정
	User search(String id);						// 회원 정보 조회
	void delete(String id);						// 회원 탈퇴
	String password(String email);
}
