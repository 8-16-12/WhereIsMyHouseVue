package com.ssafy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.dto.House;
import com.ssafy.dto.Interest;
import com.ssafy.dto.PageBean;
import com.ssafy.model.service.AddressService;
import com.ssafy.model.service.HouseService;
import com.ssafy.model.service.InterestService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/house")
public class HouseController {
	 
	private final Logger logger = LoggerFactory.getLogger(HouseController.class);
	
	@Autowired
	HouseService houseService;
	@Autowired
	AddressService addressService;
	@Autowired
	InterestService interestservice;
	
	private static List<House> houses;
	
	// 유림 ////////////////////////////////////////////////////////////////////////////////////////
//	@GetMapping("/sortingAlgorithm")

//	private String sortingbyprice(HttpServletRequest request, HttpServletResponse response) {
//		// 아파트 정렬해서 출력하기 - house의 거래매매가격 house.getDealAmount()
//		houses.sort(new House());
//		request.setAttribute("houses", houses);
//		return "house/list";
//	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/registInterest")
	private String registInterest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("here.............................!!!!!!!!!!!!!!!!");
		String id = request.getParameter("id");
		String aptName = request.getParameter("aptName");
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String sidoCode = addressService.Name2Code_Sido(sidoName).getSidoCode();
		String gugunCode = addressService.Name2Code_Gugun(gugunName).getGugunCode();
		String dongCode = addressService.Name2Code_Dong(dongName).getDongCode();
		
		System.out.println(sidoCode +" "+ gugunCode +" "+ dongCode);
		
		houses = houseService.searchApt(aptName);
		for (House house : houses) {
			int aptCode = house.getAptCode();
			String lat = house.getLat();
			String lng = house.getLng();
			
			Interest interest = new Interest(id,aptCode,aptName,sidoCode,sidoName,gugunCode,gugunName,dongCode,dongName,lat,lng);
			System.out.println(interest.toString());
			interestservice.insert(interest);
		}
		return "redirect:/index";
	}
	
// REST------------------------	
//	@GetMapping("/searchAptForm")
//	private String getSearchAptForm() {
//		System.out.println("SearchForm..........AptName");
//		return "house/searchApt";
//	}
//	@GetMapping("/searchApt/{aptName}")
//	public ResponseEntity<?> searchApt(@PathVariable String aptName) {
//		List<House> houses = houseService.searchApt(aptName);
//		if (houses != null) {
//			return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
	
	// 전체 검색 폼 - complete 
	@GetMapping("/searchForm")
	private String getSearchForm() {
		System.out.println("SearchForm..........All");
		return "house/search";
	}
	
	// 동 검색 - complete 
	@GetMapping("/searchDongForm")
	private String getSearchDongForm() {
		System.out.println("SearchForm..........Dong");
		return "house/searchDong";
	}
	@GetMapping("/searchDong")
	private String searchDong(HttpServletRequest request, HttpServletResponse response) {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		
		System.out.println(sido +" "+ gugun +" "+ dong);
		
		// 시도 입력 
		if (!sido.equals(""))
			sido = addressService.Code2Name_Sido(sido).getSidoName();
		else {
			houses = houseService.searchAll();
			request.setAttribute("houses", houses);
			return "house/list";
		}
		
		//......................... 서울이 아닐 경우
		if(!sido.equals("서울특별시")) {
			System.out.printf("NOT SEOUL.............!!!!!!" + sido );
			request.setAttribute("sido", sido);
			return "house/nothing";
		}

		// 구군 입력 
		if (!gugun.equals(""))
			gugun = addressService.Code2Name_Gugun(gugun).getGugunName();
		else {
			houses = houseService.searchSido(sido);
			request.setAttribute("houses", houses);
			return "house/list";
		}
		
		// 동 입력
		if (!dong.equals(""))
			dong = addressService.Code2Name_Dong(dong).getDongName();
		else {
			houses = houseService.searchGugun(sido, gugun);
			request.setAttribute("houses", houses);
			return "house/list";
		}
		
		houses = houseService.searchDong(sido, gugun, dong);
		if (houses.size() == 0) {
			request.setAttribute("sido", sido+' '+gugun+' '+dong);
			return "house/nothing";
		} else {
			request.setAttribute("houses", houses);
			return "house/list";
		}
	}
	
	// 아파트 이름 검색 - complete
	@GetMapping("/searchAptForm")
	public String searchAptForm() {
		System.out.println("SearchForm..........AptName");
		return "house/searchApt";
	}
	@GetMapping("/searchApt")
	private String searchApt(HttpServletRequest request, HttpServletResponse response) {
		String aptName = request.getParameter("aptName");
		houses = houseService.searchApt(aptName);
		if (houses.size() == 0)
			return "house/nothing_apt";
		request.setAttribute("houses", houses);
		return "house/list";
	}
}
