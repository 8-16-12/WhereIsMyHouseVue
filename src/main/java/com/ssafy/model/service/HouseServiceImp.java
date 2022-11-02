package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.dto.House;
import com.ssafy.model.dao.HouseDao;
import com.ssafy.model.dao.HouseDaoImp;

public class HouseServiceImp implements HouseService {
	private static HouseService instance = new HouseServiceImp();
	private HouseDao dao = HouseDaoImp.getInstance();
	
	private HouseServiceImp() {}
	public static HouseService getInstance() {
		return instance;
	};
	
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
	
	@Override
	public House search(int no) throws SQLException {
		return dao.search(no);
	}

	@Override
	public List<House> searchAll() throws SQLException {
		return dao.searchAll();
	}

	@Override
	public List<House> searchApt(String aptName) throws SQLException {
		return dao.searchApt(aptName);
	}
	@Override
	public List<House> searchDong(String sidoName, String gugunName, String dongName)
			throws SQLException {
		return dao.searchDong(sidoName, gugunName, dongName);
	}
	/*
	@Override
	public void remove(int no) throws SQLException {
		dao.remove(no);
	}
	*/

	@Override
	public String aptName2DongCode(String aptName) throws SQLException {
		return dao.aptName2DongCode(aptName);
	}

}
