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

//	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		String action = request.getServletPath();
//		System.out.println("action........." + action);
//		String url = "index.jsp";
//		/****************************/
////		if (publicKey == null) {
////	        HashMap<String, String> rsaKeyPair = createKeypairAsString();
////	        //publicKey = rsaKeyPair.get("publicKey");
////	        //privateKey = rsaKeyPair.get("privateKey");
////	        System.out.println("public" + publicKey);
////	        System.out.println("private" + privateKey);
////	        
////		}
//		/****************************/
//		
//		try {
//			switch(action) {
//			case "/insert.do":
//				url = insert(request, response); break;
//			case "/login.do":
//				url = login(request, response); break;
//			case "/search.do":
//				url = search(request, response); break;
//			case "/update.do":
//				url = update(request, response); break;
//			case "/delete.do":
//				url = delete(request, response); break;
//			case "/logout.do":
//				url = logout(request, response); break;
//
//			case "/searchApt.do":
//				url = searchApt(request, response);	break;
//			case "/selectInterest.do":
//				url = selectInterest(request, response); break;
//				
//			case "/registInterest.do":
//				url = registInterest(request, response); break;
//			case "/dropdownInterest.do":
//				url = dropdownInterest(request, response); break;
//			// 유림 ////////////////////////////////////////////////////////////////////////////////////////
//			case "/sortingAlgorithm.do":
//				url = sortingbyprice(request, response); break;
//			//////////////////////////////////////////////////////////////////////////////////////////////
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			url = "error/error.jsp";
//		}
//		
//		try {
//			switch(action) {
//				case "/searchDong.do":
//				url = searchDong(request, response); break;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			url = "house/nothing.jsp";
//		}
//		
//		if (url.startsWith("redirect:")) {
//			System.out.println("redirect.........");
//			response.sendRedirect(url.substring(9));
//		} else {
//			request.getRequestDispatcher(url).forward(request, response);
//		}
//	}
	
	// 유림 ////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/sortingAlgorithm")
	private String sortingbyprice(HttpServletRequest request, HttpServletResponse response) {
		// 아파트 정렬해서 출력하기 - house의 거래매매가격 house.getDealAmount()
		houses.sort(new House());
		request.setAttribute("houses", houses);
		return "house/list";
	}
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
	@GetMapping("/searchApt")
	private String searchApt(HttpServletRequest request, HttpServletResponse response) {
		String aptName = request.getParameter("aptName");
//		List<House> houses = houseService.searchApt(aptName);
		
		// 유림 ////////////////////////////////////////////////////////////////////////////////////////
		houses = houseService.searchApt(aptName);
		//////////////////////////////////////////////////////////////////////////////////////////////
		
		if (houses.size() == 0)
			return "house/nothing_apt";
		request.setAttribute("houses", houses);
		return "house/list";
	}
	
	
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
