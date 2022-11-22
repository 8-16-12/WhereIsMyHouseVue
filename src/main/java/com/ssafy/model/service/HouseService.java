package com.ssafy.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.dto.Criteria;
import com.ssafy.dto.House;

public interface HouseService {
	//void insert(House house) throws SQLException;
	//void update(House house) throws SQLException;
	House search(int no);
	
	List<House> searchAll(Criteria cri);
	List<House> searchApt(String aptName, Criteria cri);
	List<House> searchInterest(String aptName);
	List<House> searchDong(String sidoName, String gugunName, String dongName, Criteria cri);
	List<House> searchGugun(String sidoName, String gugunName, Criteria cri);
	List<House> searchSido(String sidoName, Criteria cri);
	
	int getTotal_All();
	
	String aptName2DongCode(String aptName);
	//void remove(int no) throws SQLException;
}
