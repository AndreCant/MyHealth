package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/profile")
	public String profile (Model model) throws BusinessException
	{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/admin/myProfile"; 
	}
	
	@GetMapping(value="/users")
	public String users (Model model) throws BusinessException{	
		model.addAttribute("users", userService.findAllUsers());
		return "private/admin/users";
	}
	
	@GetMapping(value="/user/delete")
	public String deleteUser (@RequestParam("id") Long id, Model model) throws BusinessException{	
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}
}
