package com.ssafy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.dto.Criteria;
import com.ssafy.dto.House;
import com.ssafy.dto.Interest;
import com.ssafy.dto.PageMaker;
import com.ssafy.model.service.AddressService;
import com.ssafy.model.service.HouseService;
import com.ssafy.model.service.InterestService;

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
		String id = request.getParameter("id");
		String aptName = request.getParameter("aptName");
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String sidoCode = addressService.Name2Code_Sido(sidoName).getSidoCode();
		String gugunCode = addressService.Name2Code_Gugun(gugunName).getGugunCode();
		String dongCode = addressService.Name2Code_Dong(dongName).getDongCode();
		
		houses = houseService.searchInterest(aptName);
		for (House house : houses) {
			int aptCode = house.getAptCode();
			String lat = house.getLat();
			String lng = house.getLng();
			
			Interest interest = new Interest(id,aptCode,aptName,sidoCode,sidoName,gugunCode,gugunName,dongCode,dongName,lat,lng);
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
		
		String pageNum = request.getParameter("pageNum");
		String amount = request.getParameter("amount");
		
		Criteria cri = new Criteria();
		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));
		
		String sidoName, gugunName, dongName;
		// 시도 입력 
		if (!sido.equals(""))
			sidoName = addressService.Code2Name_Sido(sido).getSidoName();
		else {
			houses = houseService.searchAll(cri);
			
			int total = houseService.getTotal_All();
			PageMaker pagemaker = new PageMaker(cri, total);
			
			request.setAttribute("houses", houses);
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			request.setAttribute("dong", dong);
			System.out.println(sido +" "+ gugun +" "+ dong);
			request.setAttribute("pagemaker", pagemaker);
			return "house/list";
		}
		
		//......................... 서울이 아닐 경우
		if(!sidoName.equals("서울특별시")) {
			System.out.printf("NOT SEOUL.............!!!!!!" + sidoName );
			request.setAttribute("sido", sidoName);
			return "house/nothing";
		}

		// 구군 입력 
		if (!gugun.equals(""))
			gugunName = addressService.Code2Name_Gugun(gugun).getGugunName();
		else {
			houses = houseService.searchSido(sidoName, cri);
			int total = houseService.getTotal_Sido(sidoName);

			PageMaker pagemaker = new PageMaker(cri, total);
			
			request.setAttribute("houses", houses);
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			request.setAttribute("dong", dong);
			request.setAttribute("pagemaker", pagemaker);
			return "house/list";
		}
		
		// 동 입력
		if (!dong.equals(""))
			dongName = addressService.Code2Name_Dong(dong).getDongName();
		else {
			houses = houseService.searchGugun(sidoName, gugunName, cri);
			int total = houseService.getTotal_Gugun(sidoName, gugunName);

			PageMaker pagemaker = new PageMaker(cri, total);
			
			request.setAttribute("houses", houses);
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			request.setAttribute("dong", dong);
			request.setAttribute("pagemaker", pagemaker);
			return "house/list";
		}
		
		houses = houseService.searchDong(sidoName, gugunName, dongName, cri);
		int total = houseService.getTotal_Dong(sidoName, gugunName, dongName);

		PageMaker pagemaker = new PageMaker(cri, total);
		if (houses.size() == 0) {
			request.setAttribute("sido", sidoName+' '+gugunName+' '+dongName);
			return "house/nothing";
		} else {
			request.setAttribute("houses", houses);
			request.setAttribute("sido", sido);
			request.setAttribute("gugun", gugun);
			request.setAttribute("dong", dong);
			request.setAttribute("pagemaker", pagemaker);
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
		System.out.println("Im here............!!!!!!!!");
		
		String aptName = request.getParameter("aptName");
		
		String pageNum = request.getParameter("pageNum");
		String amount = request.getParameter("amount");
		
		Criteria cri = new Criteria();
		cri.setPageNum(Integer.parseInt(pageNum));
		cri.setAmount(Integer.parseInt(amount));
		
		houses = houseService.searchApt(aptName, cri);
		int total = houseService.getTotal_Apt(aptName);

		PageMaker pagemaker = new PageMaker(cri, total);
		
		if (houses.size() == 0)
			return "house/nothing_apt";
		request.setAttribute("houses", houses);
		request.setAttribute("aptName", aptName);
		request.setAttribute("pagemaker", pagemaker);
		return "house/list2";
	}
}
