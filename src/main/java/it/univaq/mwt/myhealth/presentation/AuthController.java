package it.univaq.mwt.myhealth.presentation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping(value="signIn")
	public String signIn() {
		return "public/signIn";
	}
	
	@GetMapping(value="signUp")
	public String signUp(Model model) {
		model.addAttribute("user", new User());
		return "public/signUp";
	}
	
	@PostMapping(value="signUp")
	public String createUser(@Valid @ModelAttribute("user") User user, Errors errors) throws BusinessException{
		
		if (errors.hasErrors()) {
			return "public/signUp";
		}
		user.setPassword((new BCryptPasswordEncoder()).encode(user.getPassword()));
		user.setRole(userService.findRoleByName("patient"));
		userService.saveUser(user);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        if (usernamePasswordAuthenticationToken.isAuthenticated())  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		return "redirect:/";
	}
	
	@GetMapping(value="logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) new SecurityContextLogoutHandler().logout(request, response, authentication);
		
		return "redirect:/";
	}
}
