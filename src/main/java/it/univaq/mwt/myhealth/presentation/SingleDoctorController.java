package it.univaq.mwt.myhealth.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.util.Utility;

@Controller
@RequestMapping("doctors")
public class SingleDoctorController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private ReviewService reviewService;
	
	@GetMapping("/{id}")
	public String doctor (Model model, @PathVariable("id") Long id) throws BusinessException {	
		User doctor = userService.findUserById(id);
		List<Review> reviews = reviewService.findReviewsByDoctor(doctor.getId());
		int raiting = Utility.getRaiting(reviews);
		
		model.addAttribute("doctor", doctor);
		model.addAttribute("raiting", raiting);
		return "public/singleDoctor";
	}

}
