package com.ssafy.model.dao;

import java.util.List;
import java.util.Map;
import com.ssafy.dto.House;

public interface HouseDao {
	//void insert(House house) throws SQLException;
	//void update(House house) throws SQLException;
	House search(int no);
	
	// 페이지 만들기 - 1122:1015
	// List<House> getListPaging(Criteria cri);
	
	List<House> searchAll(Map<String, String> map);
	List<House> searchApt(Map<String, String> map);
	List<House> searchInterest(String aptName);
	List<House> searchDong(Map<String, String> map);
	List<House> searchGugun(Map<String, String> map);
	List<House> searchSido(Map<String, String> map);
	
	String aptName2DongCode(String aptName);
	
	int getTotal_All();
	
	//void remove(int no) throws SQLException;
}
