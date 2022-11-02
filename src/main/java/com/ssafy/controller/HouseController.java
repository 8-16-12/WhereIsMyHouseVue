package com.ssafy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.dto.House;
import com.ssafy.model.service.HouseService;

@Controller
@RequestMapping("/house")
public class HouseController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseController.class);

	@Autowired
	private HouseService houseService;
		
	@GetMapping("/search")
	public void searchForm() {}
	
	@GetMapping("/searchDong")
	public void searchDongForm() {}

//	@GetMapping("/searchApt")
//	public void searchAptForm() {}
	
	@GetMapping("/searchApt")
	public String searchAptName(String aptName, Model model) {
		logger.debug("searchApt...............aptName : {}", aptName);
		List<House> searchAptList = houseService.searchApt(aptName);
		logger.debug("searchApt...............apt : {}", searchAptList);
		model.addAttribute("searchAptList", searchAptList);
		
		return "house/searchApt";
	}
	
//	searchAll(String aptName)
//	searchDong(String sidoName, String gugunName, String dongName)
//	aptName2DongCode(String aptName)

}
