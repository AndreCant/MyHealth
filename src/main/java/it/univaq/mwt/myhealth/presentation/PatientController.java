package it.univaq.mwt.myhealth.presentation;

import java.util.List;

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
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.User;
import org.springframework.validation.Errors;

@Controller
@RequestMapping("patient")
public class PatientController {

	@Autowired
	private UserService userService;	
	

	@Autowired
	private ReservationService reservationService;	
		
   @GetMapping(value="/profile")
   public String admin (Model model) throws BusinessException
   {	
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
		User user = userService.findUserByUsername(userDetails.getUsername());
		model.addAttribute("user", user);	      
		return "private/patient/myProfile"; 
   }
   
 
   @PostMapping(value="/profile")
   public String changeProfile (@Valid @ModelAttribute("user") User user, Errors errors ) throws BusinessException {
<<<<<<< Updated upstream
	   System.out.print(user.getRole());
=======
		User currentUser = userService.findUserById(user.getId());
>>>>>>> Stashed changes
	   if (!errors.hasErrors()) {
		   currentUser.setEmail(user.getEmail());
		   currentUser.setName(user.getName());
		   currentUser.setSurname(user.getSurname());
		   currentUser.setDateOfBirth(user.getDateOfBirth());
		   userService.updateUser(currentUser);
	   }
	   return "redirect:/patient/profile";
   }
   
   @GetMapping(value="/reservations")
   public String reservationList (Model model) throws BusinessException
   {	
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();	
		User user = userService.findUserByUsername(userDetails.getUsername());
		List<Reservation> reservation = reservationService.findReservationsByPatient(user.getId());
		model.addAttribute("reservations", reservation);
		return "private/patient/dashboardMyListings"; 
   }
   
   
}
