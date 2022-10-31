package com.cohort.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println("inside index()");
		return "index";
	}
	
	@RequestMapping("/registrationPage")
	public String registration() {
		
		return "registration";
	}
	
	
	
	
	

}


	
	


