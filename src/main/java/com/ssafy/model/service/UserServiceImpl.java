package com.ssafy.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.User;
import com.ssafy.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	
	@Transactional
	public void insert(User user) {
		System.out.println("insert들어옹");
		User find = dao.search(user.getId());
		if(find != null) {
			throw new RuntimeException("이미 등록된 id 입니다.");
		}
		dao.insert(user);
	}
	
	@Transactional
	public void update(User user) {
		dao.update(user);
	}
	
	@Transactional(readOnly = true)
	public User search(String id) {
		return dao.search(id);
	}
	
	@Transactional
	public void delete(String id) {
		System.out.println("delete................................");
		dao.delete(id);
	}

}
