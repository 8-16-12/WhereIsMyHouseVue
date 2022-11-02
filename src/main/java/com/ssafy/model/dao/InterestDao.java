package com.ssafy.model.dao;

import java.util.List;

import com.ssafy.dto.Interest;

public interface InterestDao {
	
	void insert(Interest interest);
	
	Interest searchByapt(String aptname);
	
	List<String> search(String id);
	
	/*Interest search(Interest interest) throws SQLException;	
	void delete(String id) throws SQLException;	*/				
}
