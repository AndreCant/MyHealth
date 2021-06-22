package it.univaq.mwt.myhealth.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	@GetMapping(value="signIn")
	public String signIn() {
		return "common/signIn";
	}
}
