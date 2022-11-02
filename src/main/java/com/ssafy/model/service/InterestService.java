package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.dto.Interest;

public interface InterestService {
	
	void insert(Interest interest);
	
	Interest searchByapt(String aptname);
	
	List<String> search(String id);
	/*Interest search(Interest interest) throws SQLException;	
	void delete(String id) throws SQLException;	*/
}
