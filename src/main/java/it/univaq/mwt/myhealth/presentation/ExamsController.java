package it.univaq.mwt.myhealth.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.domain.Exam;

@Controller
@RequestMapping("exams")
public class ExamsController {
		
	@Autowired
	private ExamService examService;
	
	@GetMapping(value="/")
	public String all (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findAllExams());
		model.addAttribute("type", "All Exams");
		return "/public/exams";
	}
	
	@GetMapping(value="/exam")
	public String exams (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findExamsByType("exam"));
		model.addAttribute("type", "Specific Exams");
		return "/public/exams";
	}
	
	@GetMapping(value="/path")
	public String paths (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findExamsByType("rehabilitation path"));
		model.addAttribute("type", "Rehabilitation Paths");
		return "/public/exams";
	}

}
