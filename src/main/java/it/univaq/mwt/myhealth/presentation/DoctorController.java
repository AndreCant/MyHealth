package it.univaq.mwt.myhealth.presentation;

import java.security.Principal;
import java.util.List;

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
import it.univaq.mwt.myhealth.business.auth.UserDetailsImpl;
import it.univaq.mwt.myhealth.domain.AbstractEntity;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;

@Controller
@RequestMapping("doctor")
public class DoctorController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationService reservationService;
	

	@Autowired
	private VisitService visitService;
	
	@GetMapping(value="/profile")
	public String doctor (Model model) throws BusinessException
	{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/doctor/doctorProfile"; 
	}
	

	@GetMapping(value="/listReservation")
	public String listReservation (Model model) throws BusinessException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findUserByUsername(userDetails.getUsername());
		List<Visit> visit = visitService.findByDoctor(user.getId());
     	model.addAttribute("visits", visitService.findByDoctor(user.getId()));		 
		return "private/doctor/dashboardMyListings"; 		
	}
	
	
	
}
