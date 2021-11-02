package it.univaq.mwt.myhealth.presentation;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.util.Utility;
import net.bytebuddy.asm.Advice.This;

@Controller
@RequestMapping("exams")
public class ExamsController {
		
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value="/")
	public String all (Model model) throws BusinessException {
		List<Exam> exams = examService.findAllExams();
		model.addAttribute("exams", exams);
		model.addAttribute("type", "All Exams");
		model.addAttribute("raiting", this.setRaiting(exams));
		return "/public/exams";
	}
	
	@GetMapping(value="/exam")
	public String exams (Model model) throws BusinessException {
		List<Exam> exams = examService.findExamsByType("exam");
		model.addAttribute("exams", exams);
		model.addAttribute("type", "Specific Exams");
		model.addAttribute("raiting", this.setRaiting(exams));
		return "/public/exams";
	}
	
	@GetMapping(value="/path")
	public String paths (Model model) throws BusinessException {
		List<Exam> exams = examService.findExamsByType("rehabilitation path");
		model.addAttribute("exams", exams);
		model.addAttribute("type", "Rehabilitation Paths");
		model.addAttribute("raiting", this.setRaiting(exams));
		return "/public/exams";
	}
	
	private Map<Long,Integer> setRaiting(List<Exam> exams){
		Set<Long> examIds = new HashSet<Long>();
		Map<Long,List<Review>> reviewMap = new HashMap<Long,List<Review>>();
		Map<Long,Integer> raiting = new HashMap<Long,Integer>();
		
		for (Exam exam : exams) {
			examIds.add(exam.getId());
			raiting.put(exam.getId(), 0);
		}
		
		List<Review> reviews;
		
		try {
			reviews = reviewService.findReviewsByExamIds(examIds);
			
			if(!reviews.isEmpty()) {
				for (Review review : reviews) {
					Long key = review.getVisit().getReservation().getExam().getId();
					if (reviewMap.containsKey(key)) {
						reviewMap.get(key).add(review);
					} else {
						reviewMap.put(key, List.of(review));
					}
				}
				
				for (Long examId : reviewMap.keySet()) {
					raiting.put(examId, Utility.getRaiting(reviewMap.get(examId)));
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return raiting;
	}
	
	

}
