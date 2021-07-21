package it.univaq.mwt.myhealth.presentation;

import java.security.Principal;

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

import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.FrontOfficeService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;

@Controller
@RequestMapping("common")
public class ExamController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private FrontOfficeService frontOfficeService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private VisitService visitService;


	
	@GetMapping(value="/exams/exam/{name}")
	public String exam (Model model,@PathVariable("name") String name) throws BusinessException, DaoException {
		Reservation reservation = new Reservation();
		Review review = new Review();
		//model.addAttribute("reviews", reviewService.findReviewsByExam(id));
		model.addAttribute("review", review);
		model.addAttribute("exam", examService.findByName(name));	
		model.addAttribute("reservation", reservation);	
		return "/common/blog-single";
	}
	
	@PostMapping(value="/exams/exam/{name}")
	public String reservation (Model model, @PathVariable("name") String name,@ModelAttribute("reservation") Reservation reservation, Principal user) throws BusinessException {
		    if (user != null) 
		    {		    	
		    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		    Exam exam = examService.findByName(name);
		    FrontOffice frontOffice = frontOfficeService.findById(1);
		    reservation.setPatient(userService.findUserByUsername(currentUserName));
		    reservation.setFrontOffice(frontOffice);
		    reservation.setExam(exam);
		    reservation.setFrontOffice(frontOffice);
			reservationService.save(reservation);
			return "redirect:/common/exams/exam/{name}";
		  }else {
			  return "redirect:/common/signIn";
		  }
	}
	
	@PostMapping(value="/exams/exam/{name}/review")
	public String review (Model model,@PathVariable("name") String name, @ModelAttribute("review") Review review) throws BusinessException {
	    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		review.setPatient(userService.findUserByUsername(currentUserName));
		reviewService.save(review);
		model.addAttribute("exam",examService.findByName(name));
		return "redirect:/common/exams/exam/{name}";
			
	}
	
	
	
}
