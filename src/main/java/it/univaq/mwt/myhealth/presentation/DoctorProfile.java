package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;

@Controller
@RequestMapping("doctor")
public class DoctorProfile {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="")
	public String doctor (Model model) throws BusinessException
	{
		model.addAttribute("doctor", userService.findUserByRole(2));
		return "private/doctor/doctorProfile"; 
	}
	
}
