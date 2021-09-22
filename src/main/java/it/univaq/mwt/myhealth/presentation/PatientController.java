package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;

@Controller
@RequestMapping("patient")


public class PatientController {

	@Autowired
	private UserService userService;

		
		
   @GetMapping(value="/profile")
   public String admin (Model model) throws BusinessException
   {	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/patient/myProfile"; 
   }
}
