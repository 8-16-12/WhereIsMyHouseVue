package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.dto.Interest;
import com.ssafy.model.dao.InterestDao;
import com.ssafy.model.dao.InterestDaoImpl;

public class InterestServiceImpl implements InterestService {
	
	private static InterestService instance = new InterestServiceImpl();
	private InterestServiceImpl() {}
	public static InterestService getinstance() {
		return instance;
	}
	
	private InterestDao dao = InterestDaoImpl.getInstance();

	@Override
	public void insert(Interest interest) throws SQLException {
		dao.insert(interest);
	}
	@Override
	public List<String> search(String id) throws SQLException {
		return dao.search(id);
	}
	@Override
	public Interest searchByapt(String aptname) throws SQLException {
		return dao.searchByapt(aptname);
	}

	/*@Override
	public Interest search(String id) throws SQLException {
		return dao.search(id);
	}

	@Override
	public Interest search(Interest interest) throws SQLException {
		return dao.search(interest);
	}
	
	@Override
	public void delete(String id) throws SQLException {
		dao.delete(id);
	}*/

}
