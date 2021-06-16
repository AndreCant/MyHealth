package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/test-view")
	public void all() {
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@test.com");
		user.setPassword("admin123");
		userService.save(user);
	}
	
	
	@GetMapping(value="/about")
	public String about (){
		return "about";
	}
	
	
	
	@GetMapping(value="/doctor")
	public String doctorView (){
		return "doctor";
	}
	
	@GetMapping(value="/contact")
	public String contactUs (){
		return "contactUs";
	}
	
	@GetMapping(value="/login")
	public String login (){
		return "login";
	}
	

	@GetMapping(value="/sign-up")
	public String signUp (){
		return "signUp";
	}
	
	@GetMapping(value="/listing")
	public String listing (){
		return "listings";
	}
	
	@GetMapping(value="/home")
	public String home (){
		return "home";
	}
}
