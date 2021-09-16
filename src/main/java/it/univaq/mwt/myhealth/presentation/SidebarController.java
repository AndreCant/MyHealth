package it.univaq.mwt.myhealth.presentation;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.domain.AbstractEntity;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("private")
public class SidebarController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	

	@Autowired
	private VisitService visitService;
	
	@GetMapping(value="")
	public String doctor (Model model) throws BusinessException
	{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("doctor", userService.findUserByUsername(currentUserName));
		return "private/doctor/doctorProfile"; 
	}
	

	@GetMapping(value="/ListReservation")
	public String ListReservation (Model model, Principal principal ) throws BusinessException
	{
		model.addAttribute("visits", visitService.findByDoctor(((AbstractEntity) principal).getId()));		 
		return "private/doctor/dashboardMyListings"; 		
	}
	
	
	
}
