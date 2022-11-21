package com.ssafy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.dto.User;
import com.ssafy.model.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	private static final String SUCCESS = "success";
	
	@GetMapping("/insert")
	public void insertForm() {
	}
	
	@PostMapping("/insert")
	public String insert(User user) {
		userService.insert(user);
		return "redirect:/index";
	}
	
	@GetMapping("/login")
	public void login() {
		System.out.println("도착했나");
	}
	
	@PostMapping("/login")
	public String login(User user, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		String view = "/index";
		String kakaoid = request.getParameter("kaoemail");
		System.out.println(kakaoid);
		String kakaopass = "$%&*&@#@$@$%%223121432!!!@";
		String kakaoname = request.getParameter("kaoname");
		String kakaobirth = request.getParameter("kaobirth");
		
		if(kakaoid != "" ) {
			if(userService.search(kakaoid) == null) {
				System.out.println("카카오 new 로그인");
				User kakao = new User();
				kakao.setId(kakaoid);
				kakao.setPass(kakaopass);
				kakao.setName(kakaoname);
				kakao.setEmail(kakaoid);
				kakao.setBirth(kakaobirth);
				kakao.setAddr("");
				
				userService.insert(kakao);
				
				session.setAttribute("userinfo", kakao);			
				System.out.println("카카오 신규로그인 성공");
				System.out.println(kakao);
			} else {
				User kakao = userService.search(kakaoid);
				System.out.println(kakao);
				session.setAttribute("userinfo", kakao);			
				System.out.println("카카오 원래 로그인 성공");
			}
			return "redirect:/index";
		} else {
			
			User login = userService.search(user.getId());
			if (login != null && user.getPass().equals(login.getPass())) {
				
				session.setAttribute("userinfo", login);			
				System.out.println(session);
				view = "redirect:/";
			} else {
				model.addAttribute("msg", "로그인 실패");
				view = "redirect:/user/login";
			}
			
			return view;
		}
	}
	
	@GetMapping("/search")
	public String search(String id, HttpServletRequest request) {
		User loginuser = userService.search(id);
		request.setAttribute("user", loginuser);
		logger.debug("User.......................user:{}", loginuser);
		return "user/mypage";
	}
	
	@GetMapping("/update")
	public String update(User user, HttpServletRequest request) {
		userService.update(user);
		logger.debug("Update/////.......................user:{}", user);
		return "redirect:/user/search?id=" + user.getId();
	}
	
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		userService.delete(id);
		return "redirect:/index";
		
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/notice")
	public void notice() {}

}
