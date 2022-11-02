package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.dto.Interest;

public interface InterestService {
	
	void insert(Interest interest) throws SQLException;	
	
	Interest searchByapt(String aptname) throws SQLException;
	
	List<String> search(String id) throws SQLException;	
	/*Interest search(Interest interest) throws SQLException;	
	void delete(String id) throws SQLException;	*/
}
