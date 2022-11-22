package com.ssafy.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.Criteria;
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
	public List<House> searchAll(Criteria cri) {
		Map<String,String> map = new HashMap<>();
		map.put("start", Integer.toString(cri.getSkip()));
		map.put("len", Integer.toString(cri.getAmount()));
		return dao.searchAll(map);
	}
	
	@Transactional
	public List<House> searchSido(String sidoName, Criteria cri) {
		Map<String,String> map = new HashMap<>();
		map.put("sidoName",sidoName);
		map.put("start", Integer.toString(cri.getSkip()));
		map.put("len", Integer.toString(cri.getAmount()));
		return dao.searchSido(map);
	}

	@Transactional
	public List<House> searchGugun(String sidoName, String gugunName, Criteria cri) {
		Map<String,String> map = new HashMap<>();
		map.put("sidoName",sidoName);
		map.put("gugunName",gugunName);
		map.put("start", Integer.toString(cri.getSkip()));
		map.put("len", Integer.toString(cri.getAmount()));
		return dao.searchGugun(map);
	}

	@Transactional
	public List<House> searchDong(String sidoName, String gugunName, String dongName, Criteria cri){
		Map<String,String> map = new HashMap<>();
		map.put("sidoName",sidoName);
		map.put("gugunName",gugunName);
		map.put("dongName",dongName);
		map.put("start", Integer.toString(cri.getSkip()));
		map.put("len", Integer.toString(cri.getAmount()));
		return dao.searchDong(map);
	}
	
	@Transactional
	public List<House> searchApt(String aptName, Criteria cri) {
		Map<String,String> map = new HashMap<>();
		map.put("aptName", aptName);
		map.put("start", Integer.toString(cri.getSkip()));
		map.put("len", Integer.toString(cri.getAmount()));
		return dao.searchApt(map);
	}
	
	@Transactional
	public List<House> searchInterest(String aptName) {
		Map<String,String> map = new HashMap<>();
		map.put("aptName", aptName);
		map.put("start", "0");
		map.put("len", "1");
		return dao.searchApt(map);
	}

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
	
	@Transactional
	public int getTotal_All() {
		return dao.getTotal_All();
	}
}
