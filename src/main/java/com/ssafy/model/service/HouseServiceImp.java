package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.House;
import com.ssafy.model.dao.HouseDao;

@Service
public class HouseServiceImp implements HouseService {
	@Autowired
	private HouseDao dao;

	/*
	@Override
	public void insert(House house) throws SQLException {
		dao.insert(house);
	}

	@Override
	public void update(House house) throws SQLException {
		dao.update(house);
	}
	*/
	
	@Transactional
	public House search(int no) {
		return dao.search(no);
	}

	@Transactional
	public List<House> searchAll() {
		return dao.searchAll();
	}

	@Transactional
	public List<House> searchApt(String aptName) {
		return dao.searchApt(aptName);
	}

	
//	@Transactional
//	public List<House> searchDong(String sidoName, String gugunName, String dongName)
//		return dao.searchDong(sidoName, gugunName, dongName);
//	}
	/*
	@Override
	public void remove(int no) throws SQLException {
		dao.remove(no);
	}
	*/

	@Transactional
	public String aptName2DongCode(String aptName) {
		return dao.aptName2DongCode(aptName);
	}

}
