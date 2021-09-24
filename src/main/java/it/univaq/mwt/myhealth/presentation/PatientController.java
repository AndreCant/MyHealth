package it.univaq.mwt.myhealth.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;
import org.springframework.validation.Errors;

@Controller
@RequestMapping("patient")


public class PatientController {

	@Autowired
	private UserService userService;

		
		
   @GetMapping(value="/profile")
   public String admin (Model model) throws BusinessException
   {	
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
		User user = userService.findUserByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		 System.out.println(user.toString()+ "user");	    
		 System.out.println(userDetails.getUsername()+ "username");	    
		return "private/patient/myProfile"; 
   }
   
 
   @PostMapping(value="/profile")
   public String changeProfile (@Valid @ModelAttribute("user") User user, Errors errors ) throws BusinessException {
	   if (!errors.hasErrors()) {
	   System.out.println(user.getId() + "id");	    
	   userService.updateUser(user);
	   } else {
		   System.out.println( "errore");	    

	   }
		return "private/patient/myProfile";
   }
}
