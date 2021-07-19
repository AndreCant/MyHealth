package it.univaq.mwt.myhealth.presentation;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.FrontOfficeService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Reservation;

@Controller
@RequestMapping("common")
public class ExamsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private FrontOfficeService frontOfficeService;
	
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping(value="/exams")
	public String exams (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findAllExams());	
		return "/common/exams";
	}
	
	@GetMapping(value="/exams/{name}")
	public String exam (Model model, @PathVariable("name") String name) throws BusinessException {
		Reservation reservation = new Reservation();
		model.addAttribute("exam", examService.findByName(name));	
		model.addAttribute("reservation", reservation);	
		return "/common/blog-single";
	}
	
	@PostMapping(value="/exams/{name}")
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
		    model.addAttribute("exam", examService.findByName(name));
			reservationService.save(reservation);
			return "common/blog-single";
		  }else {
			  return "redirect:/common/signUp";
		  }
	}
	

}
