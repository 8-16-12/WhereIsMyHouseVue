package com.ssafy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ssafy.dto.Interest;
import com.ssafy.model.service.AddressService;
import com.ssafy.model.service.HouseService;
import com.ssafy.model.service.InterestService;

@Controller
@RequestMapping("/interest")
public class InterestController {
	 
	private final Logger logger = LoggerFactory.getLogger(InterestController.class);
	
	@Autowired
	HouseService houseService;
	@Autowired
	AddressService addressService;
	@Autowired
	InterestService interestservice;
	
	private static List<Interest> interests;

	@GetMapping("/dropdownInterest")
	private String dropdownInterest(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		List<String> list = interestservice.search(id);
		request.setAttribute("InterestList", list);
		System.out.println(list.toString());
		return "interest/selectInterest";
	}
	
	@GetMapping("/selectInterest")
	private String selectInterest(HttpServletRequest request, HttpServletResponse response) {
		String aptname = request.getParameter("aptName");
		interests = interestservice.searchByapt(aptname);
		request.setAttribute("interests", interests);
		return "interest/showInterest";
	}
}
