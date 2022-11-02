package com.ssafy.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.dto.Address;
import com.ssafy.dto.House;

public interface HouseDao {
	//void insert(House house) throws SQLException;
	//void update(House house) throws SQLException;
	House search(int no);

	List<House> searchAll();
	List<House> searchApt(String aptName);
	List<House> searchDong(Map<String, String> map);
	
	String aptName2DongCode(String aptName);
	//void remove(int no) throws SQLException;
}
