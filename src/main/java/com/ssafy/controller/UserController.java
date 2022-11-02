package com.ssafy.controller;

import javax.servlet.http.HttpServletRequest;
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
	public void insertForm() {
	}
	
	@PostMapping("/insert")
	public String insert(User user) {
		userService.insert(user);
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("도착했나");
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(User user, HttpServletRequest request, Model model) {
		String view = "/index";
		HttpSession session = request.getSession();
	
		if (user != null && user.getPass().equals(user.getPass())) {
			
			session.setAttribute("userinfo", user);			
			System.out.println("로그인 왜 안되냐ㅏ아ㅏㅏㅏㅏㅏㅏ");
			view = "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패");
		}
		
		return view;
	}
	
	@GetMapping("/search")
	public String search(User user, HttpServletRequest request) {
		String id = request.getParameter("id");	
		User loginuser = userService.search(id);
		request.setAttribute("user", loginuser);
		
		return "user/mypage";
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
}
