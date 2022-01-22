package it.univaq.mwt.myhealth.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;

@Controller
@RequestMapping("patient")
public class PatientController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private VisitService visitService;

	@GetMapping(value = "/profile")
	public String admin(Model model) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findUserByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		return "private/patient/myProfile";
	}

	@PostMapping(value = "/profile/update")
	public String changeProfile(@Valid @ModelAttribute("user") User user, Errors errors) throws BusinessException {

		User currentUser = userService.findUserById(user.getId());
		currentUser.setEmail(user.getEmail());
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setDateOfBirth(user.getDateOfBirth());
		userService.updateUser(currentUser);

		return "redirect:/patient/profile";
	}

	@GetMapping(value = "/reservations")
	public String reservationList(Model model) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findUserByUsername(userDetails.getUsername());
		List<Reservation> reservation = reservationService.findReservationsByPatient(user.getId());
		model.addAttribute("reservations", reservation);
		return "private/patient/dashboardMyListings";
	}

	@GetMapping(value = "/reservations/{id}")
	public String deleteReservation(@PathVariable("id") Long id) throws BusinessException {
		Reservation reservation = reservationService.findReservationById(id);
		visitService.deleteVisit(reservation.getVisit().getId());
		return "redirect:/patient/reservations";
	}

	@GetMapping(value = "/visits")
	public String visitsList(Model model) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findUserByUsername(userDetails.getUsername());
		Review review = new Review();
		List<Reservation> reservations = reservationService.findReservationsByPatient(user.getId());
		model.addAttribute("reservations", reservations);
		model.addAttribute("review", review);
		return "private/patient/visits";
	}

	@PostMapping(value = "/visits/{visit_id}")
	public String insertReview(@Valid @ModelAttribute("review") Review review,
			@ModelAttribute("reservations") Reservation reservations, @PathVariable("visit_id") long visitId,
			Errors errors) throws BusinessException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User user = userService.findUserByUsername(userDetails.getUsername());
		if (!errors.hasErrors()) {
			Visit visit = visitService.findById(visitId);
			review.setBody(review.getBody());
			review.setVote(review.getVote());
			review.setPatient(user);
			visit.setReview(review);
			reviewService.save(review);
		}
		return "redirect:/patient/visits";
	}
}
