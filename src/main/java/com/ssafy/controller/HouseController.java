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
	
	private static final String SUCCESS = "success";
	
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
	
	@GetMapping("/dropdownInterest")
	private String dropdownInterest(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		List<String> list = interestservice.search(id);
		request.setAttribute("InterestList", list);
		return "Interest/selectInterest";
	}

	// 중복제거 만들면 좋겠다
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
		
		List<House> houses = houseService.searchApt(aptName);
		
		for (House house : houses) {
			int aptCode = house.getAptCode();
			String lat = house.getLat();
			String lng = house.getLng();
			
			Interest interest = new Interest(id,aptCode,aptName,sidoCode,sidoName,gugunCode,gugunName,dongCode,dongName,lat,lng);
			interestservice.insert(interest);
		}
		return "redirect:/index";
	}
	
	
	
	@GetMapping("/searchForm")
	private String getSearchForm() {
		return "house/search";
	}

	
	
	
	@GetMapping("/searchAptForm")
	private String getSearchAptForm() {
		return "house/searchApt";
	}
	@GetMapping("/searchApt/{aptName}")
	public ResponseEntity<?> searchApt(@PathVariable String aptName) {
		List<House> houses = houseService.searchApt(aptName);
		if (houses != null) {
			return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
//	private String searchApt(HttpServletRequest request, HttpServletResponse response) {
//		String aptName = request.getParameter("aptName");
////		List<House> houses = houseService.searchApt(aptName);
//		
//		// 유림 ////////////////////////////////////////////////////////////////////////////////////////
//		houses = houseService.searchApt(aptName);
//		//////////////////////////////////////////////////////////////////////////////////////////////
//		
//		if (houses.size() == 0)
//			return "house/nothing_apt";
//		request.setAttribute("houses", houses);
//		return "house/list";
//	}
	
	
	@GetMapping("/searchDongForm")
	private String getSearchDongForm() {
		return "house/searchDong";
	}
	@GetMapping("/searchDong")
	private String searchDong(HttpServletRequest request, HttpServletResponse response) {
		String sido = request.getParameter("sido");
		String gugun = request.getParameter("gugun");
		String dong = request.getParameter("dong");
		if (!sido.equals(""))
			sido = addressService.Code2Name_Sido(sido).getSidoName();
		if (!gugun.equals(""))
			gugun = addressService.Code2Name_Gugun(gugun).getGugunName();
		if (!dong.equals(""))
			dong = addressService.Code2Name_Dong(dong).getDongName();
		
		if (sido.equals("서울특별시")) {
//			List<House> houses = houseService.searchDong(sido, gugun, dong);
			// 유림 ////////////////////////////////////////////////////////////////////////////////////////
			houses = houseService.searchDong(sido, gugun, dong);
			//////////////////////////////////////////////////////////////////////////////////////////////
			if (houses == null)
				return "house/nothing";
			request.setAttribute("houses", houses);
			return "house/list";
		}
		else {
			return "house/nothing";
		}
	}
	
	@GetMapping("/selectInterest")
	private String selectInterest(HttpServletRequest request, HttpServletResponse response) {
		String aptname = request.getParameter("aptName");
		Interest interest = interestservice.searchByapt(aptname);
		request.setAttribute("interest", interest);
		System.out.println(interest);
		
		return "Interest/showInterest";
	}
		
//	@GetMapping("/searchApt")
//	public String searchAptForm() {
//		return "house/searchApt";
//	}
//	
//	@PostMapping("/searchApt")
//	public String searchAptName(String aptName, Model model) {
//		System.out.println("searchApt...............aptName : {}");
//		logger.debug("searchApt...............aptName : {}", aptName);
//		List<House> searchAptList = houseService.searchApt(aptName);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@" + searchAptList.size());
//		logger.debug("searchApt...............apt : {}", searchAptList);
//		model.addAttribute("houses", searchAptList);
//		return "house/list";
//	}
}
