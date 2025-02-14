package com.realpro.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/python")
	@PreAuthorize("hasAuthority('user')")
	public String python() {
		return "python/python";
	}
	//https://stackoverflow.com/questions/62548747/preauthorize-returns-403
	@GetMapping("/course")
	@PreAuthorize("hasRole('user')")
	public String course() {
		return "course/course";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about/about";
	}
}
