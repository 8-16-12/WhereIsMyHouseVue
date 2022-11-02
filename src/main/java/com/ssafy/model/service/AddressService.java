package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.dto.Address;

public interface AddressService {
	Address Code2Name_Sido(String sidoCode) throws SQLException;
	Address Code2Name_Gugun(String gugunCode) throws SQLException;
	Address Code2Name_Dong(String dongCode) throws SQLException;

	Address Name2Code_Sido(String sidoName) throws SQLException;
	Address Name2Code_Gugun(String gugunName) throws SQLException;
	Address Name2Code_Dong(String dongName) throws SQLException;
}
