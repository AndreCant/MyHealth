package it.univaq.mwt.myhealth.presentation;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import antlr.collections.List;
import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.Visit;
import it.univaq.mwt.myhealth.util.Utility;

@Controller
@RequestMapping("exam")
public class ExamController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private AdministrationService administrationService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private VisitService visitService;


	
	@GetMapping(value="/{name}")
	public String exam (Model model,@PathVariable("name") String name) throws BusinessException, DaoException {
		Reservation reservation = new Reservation();
		Visit visit = new Visit();
		Review review = new Review();
		HashSet<Long> reviews = new HashSet<Long>();
		reviews.add(examService.findByName(name).getId());
		model.addAttribute("reviews", reviewService.findReviewsByExamIds(reviews));
		model.addAttribute("review", review);
		model.addAttribute("visit", visit);
		model.addAttribute("exam", examService.findByName(name));	
		model.addAttribute("reservation", reservation);	
		return "/public/singleExam";
	}
	
	@PostMapping(value="/{name}")
	public String reservation (Model model, @PathVariable("name") String name, @ModelAttribute("reservation") Reservation reservation, @ModelAttribute("visit") Visit visit, final RedirectAttributes redirectAttributes) throws BusinessException {			    
		    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		    Exam exam = examService.findByName(name);
		    ArrayList<Reservation> l =  (ArrayList<Reservation>) reservationService.findAllReservations();
		    ArrayList<LocalDateTime> update_l = new ArrayList<>();
		    for(int i=0; i<l.size();i++) {
		    	update_l.add(l.get(i).getStartHour());	    	
		    }
		    if(!update_l.contains(reservation.getStartHour())) {
   			 FrontOffice frontOffice = administrationService.findFrontOfficeById((long)Utility.getRandomNumberInRange(1, 5));
   	  		    reservation.setPatient(userService.findUserByUsername(currentUserName));
   	  		    reservation.setExam(exam);
   	  		    reservation.setFrontOffice(frontOffice);
   	  		    visit.setReservation(reservation);
   	  		    visit.setDoctor(userService.findRandomDoctor(Utility.getRandomNumberInRange(2, 3)));
   	  		    reservation.setVisit(visit);
   	  		    visitService.saveVisit(visit);
   	  			reservationService.save(reservation);
   			    redirectAttributes.addFlashAttribute("message_success", "this is the message");	    	
   	  			return "redirect:/exam/{name}";	
   		    }            
		    redirectAttributes.addFlashAttribute("message", "this is the message");	    	
		    return "redirect:/exam/{name}";
	}
	
	@PostMapping(value="/{name}/review")
	public String review (Model model,@PathVariable("name") String name, @ModelAttribute("review") Review review) throws BusinessException {
	    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		review.setPatient(userService.findUserByUsername(currentUserName));
		reviewService.save(review);
		model.addAttribute("exam",examService.findByName(name));
		return "redirect:/exam/{name}";			
	}
	
}
