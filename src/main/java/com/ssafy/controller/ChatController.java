package com.ssafy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	@GetMapping("/chatForm")
	private String getChatForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("chatForm...............");
		return "chat/chatForm";
	}

	
	@GetMapping("")
	private String chatting(HttpServletRequest request, HttpServletResponse response) {
		String nickName = request.getParameter("nickName");
		
		System.out.println("chatting..............." + request.getParameter("nickName"));
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		
		request.setAttribute("nickName", nickName);
		return "chat";
	}
}
