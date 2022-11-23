package com.ssafy.happyhouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping({"/", "/index"})
	public String index() {
		System.out.println("MainController..................................index");
		return "index";
	}
	
	@GetMapping({"/qna"})
	public String qna() {
		return "qna";
	}
	
	@GetMapping({"/notice"})
	public String notice() {
		return "notice";
	}
	
	@GetMapping({"/aqna"})
	public String aqna() {
		return "aqna";
	}
	
	@GetMapping({"/anotice"})
	public String anotice() {
		return "anotice";
	}
}