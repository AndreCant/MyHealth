package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;

@Controller
@RequestMapping("doctors")
public class SingleDoctorController {
	

	@Autowired
	private UserService userService;
	
	@GetMapping("/{name}")
	public String doctor (Model model, @PathVariable("name") String name) throws BusinessException {		
		model.addAttribute("doctor", userService.findByName( name));
		return "public/singleDoctor";
	}

}
