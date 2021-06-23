package it.univaq.mwt.myhealth.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("common")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="signIn")
	public String signIn() {
		return "common/signIn";
	}
	
	@GetMapping(value="signUp")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "common/signUp";
	}
	
	@PostMapping(value="signUp")
	public String createUser(@Valid @ModelAttribute("user") User user, Errors errors) throws BusinessException{
		if (errors.hasErrors()) {
			return "common/signUp";
		}
		user.setPassword((new BCryptPasswordEncoder()).encode(user.getPassword()));
		user.setRole(userService.findRoleByName("patient"));
		userService.saveUser(user);

		return "redirect:/home";
	}
}
