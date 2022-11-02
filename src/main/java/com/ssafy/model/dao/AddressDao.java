package com.ssafy.model.dao;

import java.sql.SQLException;

import com.ssafy.dto.Address;

public interface AddressDao {
	Address Code2Name_Sido(String sidoCode);
	Address Code2Name_Gugun(String gugunCode);
	Address Code2Name_Dong(String dongCode);
	
	Address Name2Code_Sido(String sidoName);
	Address Name2Code_Gugun(String gugunName);
	Address Name2Code_Dong(String dongName);
}
