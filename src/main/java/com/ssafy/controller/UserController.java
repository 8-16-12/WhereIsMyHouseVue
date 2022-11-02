package com.ssafy.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.dto.User;
import com.ssafy.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/insert")
	public void insert() {
		System.out.println("웨 않대ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ");
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute User user, Model model) {	
		userService.insert(user);
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("도착했나");
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		String view = "/user/index";
		User loginUser = user;
		if (loginUser != null && user.getPass().equals(loginUser.getPass())) {
			session.setAttribute("loginUser", loginUser);
			view = "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패");
		}
		
		return view;
	}
	
}
