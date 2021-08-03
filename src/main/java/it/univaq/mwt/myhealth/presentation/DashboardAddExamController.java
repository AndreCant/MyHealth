package it.univaq.mwt.myhealth.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;
import org.springframework.ui.Model;


@Controller
@RequestMapping("admin")
public class DashboardAddExamController {
	
	@Autowired
	private ExamService examService;
	
	@GetMapping(value="/addExam")
	public String addExam(Model model) {
		Exam exam = new Exam();
		model.addAttribute("exam",exam);	
		return "private/admin/dashboardAddExam";		
	}
	
	@PostMapping (value="/addExam")
	public String insertExam (@ModelAttribute("exam") Exam exam) {
		try {
			examService.save(exam);
			return "redirect:addExam";
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
