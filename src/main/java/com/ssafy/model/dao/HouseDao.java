package com.ssafy.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.dto.Address;
import com.ssafy.dto.House;

public interface HouseDao {
	//void insert(House house) throws SQLException;
	//void update(House house) throws SQLException;
	House search(int no) throws SQLException;

	List<House> searchAll() throws SQLException;
	List<House> searchApt(String aptName) throws SQLException;
	List<House> searchDong(String sidoName, String gugunName, String dongName) throws SQLException;
	
	String aptName2DongCode(String aptName) throws SQLException;
	//void remove(int no) throws SQLException;
}
