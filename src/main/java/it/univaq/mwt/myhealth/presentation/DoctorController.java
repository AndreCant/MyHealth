package it.univaq.mwt.myhealth.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;
import it.univaq.mwt.myhealth.util.ObjectFactory;

@Controller
@RequestMapping("doctor")
public class DoctorController {

	@Autowired private UserService userService;
	@Autowired private VisitService visitService;
	@Autowired private AdministrationService administrationService;
	@Autowired private DocumentService documentService;
	
	@GetMapping(value="/profile")
	public String doctor (Model model) throws BusinessException
	{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/doctor/doctorProfile"; 
	}
	
	@PostMapping(value="/profile")
	public String updateProfile (@Valid @ModelAttribute("user") User user, Errors errors) throws BusinessException {	
		User currentUser = userService.findUserById(user.getId());
		currentUser.setEmail(user.getEmail());
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setFiscalCode(user.getFiscalCode());
		currentUser.setGender(user.getGender());
		currentUser.setDateOfBirth(user.getDateOfBirth());
		userService.updateUser(currentUser);
		return "redirect:/doctor/profile";
	}
	
	@GetMapping(value="/visits")
	public String listReservation (Model model) throws BusinessException{
		User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Visit> visit = visitService.findByDoctor(user.getId());
     	model.addAttribute("visits", visitService.findByDoctor(user.getId()));		 
		return "private/doctor/dashboardMyListings"; 		
	}
	
	@GetMapping(value="/visit/complete")
	public String completeVisit (@RequestParam("id") Long id, Model model) throws BusinessException{	
		Visit visit = visitService.findById(id);
		visit.setCompleted(true);
		visitService.updateVisit(visit);
		return "redirect:/doctor/visits";
	}
	
	@GetMapping(value="/diagnosis/create")
	public String createDiangosis (@RequestParam("visitId") Long visitId, Model model) throws BusinessException{	
		model.addAttribute("visitId", visitId);	
		model.addAttribute("medicines", administrationService.findAllMedicines());
		model.addAttribute("medicine", "");
		model.addAttribute("diagnosis", new Diagnosis());
		return "private/doctor/diagnosisForm";
	}
	
	@PostMapping(value="/diagnosis/create")
	public String updateMedicinePost (@Valid @RequestParam("visitId") Long visitId, @ModelAttribute("diagnosis") Diagnosis diagnosis, @ModelAttribute("medicine") Long medicineId, Errors errors) throws BusinessException{
		Visit visit = visitService.findById(visitId);
		visit.setDiagnosis(diagnosis);
		visitService.saveDiagnosis(List.of(diagnosis));
		visitService.updateVisit(visit);
		
		if (medicineId != 0) {
			Medicine medicine = administrationService.findMedicineById(medicineId);
			MedicineDiagnosis relation = ObjectFactory.createMedicineDiagnosis(medicine, diagnosis);
			visitService.saveMedicineDiagnosis(List.of(relation));
		}
		
		return "redirect:/doctor/visits";
	}
	
	@GetMapping(value="/patients")
	public String patients (Model model) throws BusinessException{
		User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Visit> visits = visitService.findByDoctor(user.getId());
		Set<User> patients = new HashSet<User>();
		Map<Long,Integer> visitMap = new HashMap<>();
		
		for (Visit visit : visits) {
			User patient = visit.getReservation().getPatient();
			patients.add(patient);
			
			if (visitMap.containsKey(patient.getId())) {
				visitMap.put(patient.getId(), visitMap.get(patient.getId()) + 1); 
			}else {
				visitMap.put(patient.getId(), 1);
			}
		}
		
     	model.addAttribute("users", patients);	
     	model.addAttribute("visitMap", visitMap);
     	
		return "private/doctor/patients"; 		
	}
	
	@GetMapping(value="/medicines")
	public String medicines (Model model) throws BusinessException{	
		model.addAttribute("medicines", administrationService.findAllMedicines());
		return "private/doctor/medicines";
	}
	
	@GetMapping(value="/diagnosis")
	public String diagnosis (Model model) throws BusinessException{	
		User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Visit> visits = visitService.findByDoctor(user.getId());
		Set<Diagnosis> diagnosis = new HashSet<Diagnosis>();
		
		for (Visit visit : visits) {
			if (visit.getDiagnosis() != null) diagnosis.add(visit.getDiagnosis());
		}
		
     	model.addAttribute("diagnosis", diagnosis);	
     	
		return "private/doctor/diagnosis";	
	}
	
	@GetMapping(value="/diagnosis/delete")
	public String deleteDiagnosis (@RequestParam("id") Long id, Model model) throws BusinessException{	
		List<Visit> visits = visitService.findVisitsByDiagnosis(id);
		for (Visit visit : visits) {
			visit.setDiagnosis(null);
			visitService.updateVisit(visit);
		}
		visitService.deleteDiagnosis(id);
		return "redirect:/doctor/diagnosis";
	}
	
	@GetMapping(value="/paychecks")
	public String paychecks (Model model) throws BusinessException{	
		User doctor = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("paychecks", documentService.findPaychecksByRegister(doctor.getRegister()));
		return "private/admin/paychecks";
	}
}
