package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping("/Login")
	public String showLoginForm() {
		return "fancy-login";
		
	}
	
	@RequestMapping("/AccessDenied")
	public String showAccessDeniedPage() {
		return "access-denied";
		
	}
	
	
	
}
