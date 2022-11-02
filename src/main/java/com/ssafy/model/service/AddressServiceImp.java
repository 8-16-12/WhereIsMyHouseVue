package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.dto.Address;
import com.ssafy.model.dao.AddressDao;
import com.ssafy.model.dao.AddressDaoImp;

public class AddressServiceImp implements AddressService {
	private static AddressService instance = new AddressServiceImp();
	private AddressServiceImp() {}
	public static AddressService getInstance() {
		return instance;
	}
	private AddressDao dao = AddressDaoImp.getInstance();
	
	@Override
	public Address Code2Name_Sido(String sidoCode) throws SQLException {
		return dao.Code2Name_Sido(sidoCode);
	}

	@Override
	public Address Code2Name_Gugun(String gugunCode) throws SQLException {
		return dao.Code2Name_Gugun(gugunCode);
	}

	@Override
	public Address Code2Name_Dong(String dongCode) throws SQLException {
		return dao.Code2Name_Dong(dongCode);
	}
	@Override
	public Address Name2Code_Sido(String sidoName) throws SQLException {
		return dao.Name2Code_Sido(sidoName);
	}
	@Override
	public Address Name2Code_Gugun(String gugunName) throws SQLException {
		return dao.Name2Code_Gugun(gugunName);
	}
	@Override
	public Address Name2Code_Dong(String dongName) throws SQLException {
		return dao.Name2Code_Dong(dongName);
	}
}
