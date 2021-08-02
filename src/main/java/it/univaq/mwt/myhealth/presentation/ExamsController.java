package it.univaq.mwt.myhealth.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;

@Controller
@RequestMapping("common")
public class ExamsController {
		
	@Autowired
	private ExamService examService;
	
	@GetMapping(value="/exams")
	public String exams (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findAllExams());	
		return "/public/exams";
	}

}
