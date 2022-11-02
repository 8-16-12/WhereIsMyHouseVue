package com.ssafy.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.Address;
import com.ssafy.model.dao.AddressDao;

@Service
public class AddressServiceImp implements AddressService {
	@Autowired
	private AddressDao dao;
	
	
	@Transactional
	public Address Code2Name_Sido(String sidoCode) {
		return dao.Code2Name_Sido(sidoCode);
	}

	@Transactional
	public Address Code2Name_Gugun(String gugunCode) {
		return dao.Code2Name_Gugun(gugunCode);
	}

	@Transactional
	public Address Code2Name_Dong(String dongCode) {
		return dao.Code2Name_Dong(dongCode);
	}
	@Transactional
	public Address Name2Code_Sido(String sidoName) {
		return dao.Name2Code_Sido(sidoName);
	}
	@Transactional
	public Address Name2Code_Gugun(String gugunName) {
		return dao.Name2Code_Gugun(gugunName);
	}
	@Transactional
	public Address Name2Code_Dong(String dongName) {
		return dao.Name2Code_Dong(dongName);
	}
}
