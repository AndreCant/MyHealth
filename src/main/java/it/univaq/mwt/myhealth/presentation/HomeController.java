package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String home(Model model) throws BusinessException {		
		model.addAttribute("doctors", userService.findUserByRole(1));
		return "common/home";
	}
	
	@GetMapping("/aboutUs")
	public String about() throws BusinessException {		
		return "common/about";
	}
}
