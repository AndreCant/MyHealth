package it.univaq.mwt.myhealth.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {
	
	@GetMapping("")
	public String home() {
		return "common/home";
	}
}
