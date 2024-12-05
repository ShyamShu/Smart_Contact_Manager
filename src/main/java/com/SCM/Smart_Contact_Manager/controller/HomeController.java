package com.SCM.Smart_Contact_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.SCM.Smart_Contact_Manager.forms.UserForm;



@Controller
public class HomeController {

	@GetMapping("/")
	public String index()
	{
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(org.springframework.ui.Model model ){
		model.addAttribute("name" , " Shyam Technologies ");
		model.addAttribute("youtube" , "https://www.youtube.com/watch?v=SAqi7zmW1fY&t=5013s");
		//System.out.println("hello i am good ");
		return "home";
	}
	@GetMapping("/about")
	public String about(org.springframework.ui.Model model ){
		return "about";
	}
	@GetMapping("/services")
	public String services(org.springframework.ui.Model model ){
		return "services";
	}

	@GetMapping("/contact")
	public String contact(org.springframework.ui.Model model ){
		return "contact";
	}
	@GetMapping("/login")
	public String login(org.springframework.ui.Model model ){
		return "login";
	}
	@GetMapping("/register")
	public String register(org.springframework.ui.Model model ){

		UserForm userForm = new UserForm();

		model.addAttribute("userForm", userForm);
		return "register";
	}

	@GetMapping("/error")
	public String getMethodName() {
		return "error";
	}
	

}
