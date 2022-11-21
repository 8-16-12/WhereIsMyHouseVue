package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.Interest;
import com.ssafy.model.dao.InterestDao;


@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestDao dao;
	
	@Transactional
	public void insert(Interest interest) {
		System.out.println("InterestServiceImpl..............insert");
		dao.insert(interest);
	}
	
	@Transactional
	public List<String> search(String id) {
		return dao.search(id);
	}
	
	@Transactional
	public Interest searchByapt(String aptname) {
		return dao.searchByapt(aptname);
	}

	/*@Override
	public Interest search(String id) throws SQLException {
		return dao.search(id);
	}

	@Override
	public Interest search(Interest interest) throws SQLException {
		return dao.search(interest);
	}
	
	@Override
	public void delete(String id) throws SQLException {
		dao.delete(id);
	}*/

}
