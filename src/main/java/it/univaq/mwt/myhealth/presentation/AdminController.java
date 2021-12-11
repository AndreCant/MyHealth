package it.univaq.mwt.myhealth.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Image;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.util.ObjectFactory;
import it.univaq.mwt.myhealth.util.Utility;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired private UserService userService;
	@Autowired private ExamService examService;
	@Autowired private VisitService visitService;
	@Autowired private ReservationService reservationService;
	@Autowired private AdministrationService administrationService;
	@Autowired private DocumentService documentService;
	@Autowired private ReviewService reviewService;
	
	@GetMapping(value="/profile")
	public String profile (Model model) throws BusinessException{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/admin/myProfile"; 
	}
	
	@PostMapping(value="/profile")
	public String updateProfile (@Valid @ModelAttribute("user") User user, Errors errors) throws BusinessException {	
		User currentUser = userService.findUserById(user.getId());
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setGender(user.getGender());
		currentUser.setDateOfBirth(user.getDateOfBirth());
		userService.updateUser(currentUser);
		return "redirect:/admin/profile";
	}
	
	@GetMapping(value="/users")
	public String users (Model model) throws BusinessException{	
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("loggedUsername", SecurityContextHolder.getContext().getAuthentication().getName());
		return "private/admin/users";
	}
	
	@GetMapping(value="/user/delete")
	public String deleteUser (@RequestParam("id") Long id, Model model) throws BusinessException{	
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}
	
	@GetMapping(value="/user/update")
	public String updateUser (@RequestParam("id") Long id, Model model) throws BusinessException{
		User user = userService.findUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("roleName", user.getRole().getName());
		return "private/admin/userForm";
	}
	
	@PostMapping(value="/user/update")
	public String updateUserPost (@Valid @ModelAttribute("user") User user, @ModelAttribute("roleName") String role, Errors errors) throws BusinessException{
		User currentUser = userService.findUserById(user.getId());
		currentUser.setSpecialization(user.getSpecialization());
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setGender(user.getGender());
		currentUser.setRegister(user.getRegister());
		currentUser.setSkills(user.getSkills());
		currentUser.setDateOfBirth(user.getDateOfBirth());
		currentUser.setRole(userService.findRoleByName(role));
		userService.updateUser(currentUser);
		return "redirect:/admin/users";
	}
	
	@GetMapping(value="/user/create")
	public String createUser (Model model) throws BusinessException{
		model.addAttribute("user", new User());
		model.addAttribute("roleName", "");
		return "private/admin/userForm";
	}
	
	@PostMapping(value="/user/create")
	public String createUserPost (@Valid @ModelAttribute("user") User user, @ModelAttribute("roleName") String role, Errors errors) throws BusinessException{
		user.setRole(userService.findRoleByName(role));
		user.setPassword(Utility.encodePassword(user.getPassword()));
		userService.saveUser(user);
		return "redirect:/admin/users";
	}
	
	@PostMapping("/uploadImage")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, RedirectAttributes attributes) throws BusinessException{
        if (file.isEmpty()) {
            return "redirect:/";
        }
        
        String fileName = file.getOriginalFilename();
        int indexExtension = fileName.lastIndexOf('.');
        
        if (indexExtension > 0) {
        	User user = userService.findUserById(Long.valueOf(userId));
            String fileExtension = fileName.substring(indexExtension + 1);
            String newFileName = user.getUsername() + "." + fileExtension;
            
            try {
            	Path totalPath = Paths.get("src", "main", "resources", "static", "uploads", "users", newFileName);
            	Path partialPath = Paths.get("uploads", "users", newFileName);
            	Files.deleteIfExists(totalPath);
            	Files.copy(file.getInputStream(), totalPath, StandardCopyOption.REPLACE_EXISTING);
            	
            	Image image = ObjectFactory.createImage(newFileName, "\\" + partialPath.toString(), null);
            	examService.saveImages(List.of(image));
            	user.setImage(image);
            	userService.updateUser(user);
            	
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }

        return "redirect:/admin/user/update?id=" + userId;
    }
	
	@GetMapping(value="/exams")
	public String exams (Model model) throws BusinessException{	
		model.addAttribute("exams", examService.findAllExams());
		return "private/admin/exams";
	}
	
	@GetMapping(value="/exam/delete")
	public String deleteExam (@RequestParam("id") Long id, Model model) throws BusinessException{	
		examService.delete(id);
		return "redirect:/admin/exams";
	}
	
	@GetMapping(value="/exam/create")
	public String createExam (Model model) throws BusinessException{
		model.addAttribute("exam", new Exam());
		model.addAttribute("typeName", "");
		model.addAttribute("imgUrl", "");
		return "private/admin/examForm";
	}
	
	@PostMapping(value="/exam/create")
	public String createExamPost (@Valid @ModelAttribute("exam") Exam exam, @ModelAttribute("typeName") String typeName, @ModelAttribute("imgUrl") String imgUrl, Errors errors) throws BusinessException{
		exam.setType(Utility.getExamType(typeName));
		examService.save(exam);
		Image image = ObjectFactory.createImage(null, imgUrl, exam);
    	examService.saveImages(List.of(image));
		return "redirect:/admin/exams";
	}
	
	@GetMapping(value="/exam/update")
	public String updateExam (@RequestParam("id") Long id, Model model) throws BusinessException{
		Exam exam = examService.findById(id);
		model.addAttribute("exam", exam);
		model.addAttribute("typeName", Utility.getExamType(exam.getType()));
		
		String imageUrl = exam.getImages() != null && !exam.getImages().isEmpty() ? exam.getImages().get(0).getUrl() : "";
		model.addAttribute("imgUrl", imageUrl);
		return "private/admin/examForm";
	}
	
	@PostMapping(value="/exam/update")
	public String updateExamPost (@Valid @ModelAttribute("exam") Exam exam, @ModelAttribute("typeName") String typeName, @ModelAttribute("imgUrl") String imgUrl, Errors errors) throws BusinessException{
		Exam currentExam = examService.findById(exam.getId());
		currentExam.setCode(exam.getCode());
		currentExam.setName(exam.getName());
		currentExam.setType(Utility.getExamType(typeName));
		currentExam.setPrice(exam.getPrice());
		currentExam.setSpecialization(exam.getSpecialization());
		currentExam.setSubSpecialization(exam.getSubSpecialization());
		currentExam.setSession(exam.getSession());
		currentExam.setDescription(exam.getDescription());
		examService.update(currentExam);

		if (currentExam.getImages() == null || currentExam.getImages().isEmpty()) {
	    	examService.saveImages(List.of(ObjectFactory.createImage(null, imgUrl, currentExam)));
		}
		return "redirect:/admin/exams";
	}
	
	@GetMapping(value="/diagnosis")
	public String diagnosis (Model model) throws BusinessException{	
		model.addAttribute("diagnosis", visitService.findAllDiagnosis());
		return "private/admin/diagnosis";
	}
	
	@GetMapping(value="/diagnosis/delete")
	public String deleteDiagnosis (@RequestParam("id") Long id, Model model) throws BusinessException{	
		visitService.deleteDiagnosis(id);
		return "redirect:/admin/diagnosis";
	}
	
	@GetMapping(value="/reservations")
	public String reservations (Model model) throws BusinessException{	
		model.addAttribute("reservations", reservationService.findAllReservations());
		return "private/admin/reservations";
	}
	
	@GetMapping(value="/payments")
	public String payments (Model model) throws BusinessException{	
		model.addAttribute("payments", administrationService.findAllPayments());
		return "private/admin/payments";
	}
	
	@GetMapping(value="/visits")
	public String visits (Model model) throws BusinessException{	
		model.addAttribute("visits", visitService.findAll());
		return "private/admin/visits";
	}
	
	@GetMapping(value="/invoices")
	public String invoices (Model model) throws BusinessException{	
		model.addAttribute("invoices", documentService.findAllInvoices());
		return "private/admin/invoices";
	}
	
	@GetMapping(value="/paychecks")
	public String paychecks (Model model) throws BusinessException{	
		model.addAttribute("paychecks", documentService.findAllPaychecks());
		return "private/admin/paychecks";
	}
	
	@GetMapping(value="/reviews")
	public String reviews (Model model) throws BusinessException{	
		model.addAttribute("reviews", reviewService.findAllReviews());
		return "private/admin/reviews";
	}
	
	@GetMapping(value="/review/delete")
	public String reviewExam (@RequestParam("id") Long id, Model model) throws BusinessException{	
		Review review = reviewService.findReviewById(id);
		review.setRemoved(true);
		reviewService.updateReview(review);
		
		return "redirect:/admin/reviews";
	}
	
	@GetMapping(value="/medicines")
	public String medicines (Model model) throws BusinessException{	
		model.addAttribute("medicines", administrationService.findAllMedicines());
		return "private/admin/medicines";
	}
	
	@GetMapping(value="/medicine/delete")
	public String deleteMedicine (@RequestParam("id") Long id, Model model) throws BusinessException{	
		administrationService.deleteMedicine(id);
		return "redirect:/admin/medicines";
	}
	
	@GetMapping(value="/medicine/update")
	public String updateMedicine (@RequestParam("id") Long id, Model model) throws BusinessException{
		Medicine medicine = administrationService.findMedicineById(id);
		model.addAttribute("medicine", medicine);
		return "private/admin/medicineForm";
	}
	
	@PostMapping(value="/medicine/update")
	public String updateMedicinePost (@Valid @ModelAttribute("medicine") Medicine medicine, Errors errors) throws BusinessException{
		Medicine currMedicine = administrationService.findMedicineById(medicine.getId());
		currMedicine.setCode(medicine.getCode());
		currMedicine.setName(medicine.getName());
		currMedicine.setActiveIngredient(medicine.getActiveIngredient());
		currMedicine.setWeight(medicine.getWeight());
		currMedicine.setDescription(medicine.getDescription());
		
		administrationService.updateMedicine(currMedicine);
		return "redirect:/admin/medicines";
	}
	
	@GetMapping(value="/medicine/create")
	public String createMedicine (Model model) throws BusinessException{
		model.addAttribute("medicine", new Medicine());
		return "private/admin/medicineForm";
	}
	
	@PostMapping(value="/medicine/create")
	public String createMedicinePost (@Valid @ModelAttribute("medicine") Medicine medicine, Errors errors) throws BusinessException{
		administrationService.saveMedicine(medicine);
		return "redirect:/admin/medicines";
	}
	
	@GetMapping(value="/frontOffices")
	public String frontOffices (Model model) throws BusinessException{	
		model.addAttribute("frontOffices", administrationService.findAllFrontOffices());
		return "private/admin/frontOffices";
	}
	
	@GetMapping(value="/frontOffice/delete")
	public String deleteFrontOffice (@RequestParam("id") Long id, Model model) throws BusinessException{	
		administrationService.deleteFrontOffice(id);
		return "redirect:/admin/frontOffices";
	}
	
	@GetMapping(value="/frontOffice/update")
	public String updateFrontOffice (@RequestParam("id") Long id, Model model) throws BusinessException{
		FrontOffice frontOffice = administrationService.findFrontOfficeById(id);
		model.addAttribute("frontOffice", frontOffice);
		return "private/admin/frontOfficeForm";
	}
	
	@PostMapping(value="/frontOffice/update")
	public String updateFrontOfficePost (@Valid @ModelAttribute("frontOffice") FrontOffice frontOffice, Errors errors) throws BusinessException{
		FrontOffice currFrontOffice = administrationService.findFrontOfficeById(frontOffice.getId());
		currFrontOffice.setNumber(frontOffice.getNumber());
		currFrontOffice.setName(frontOffice.getName());
		
		administrationService.updateFrontOffice(currFrontOffice);
		return "redirect:/admin/frontOffices";
	}
	
	@GetMapping(value="/frontOffice/create")
	public String createFrontOffice (Model model) throws BusinessException{
		model.addAttribute("frontOffice", new FrontOffice());
		return "private/admin/frontOfficeForm";
	}
	
	@PostMapping(value="/frontOffice/create")
	public String createFrontOfficePost (@Valid @ModelAttribute("frontOffice") FrontOffice frontOffice, Errors errors) throws BusinessException{
		administrationService.saveFrontOffices(List.of(frontOffice));
		return "redirect:/admin/frontOffices";
	}
	
	@GetMapping(value="/expenses")
	public String expenses (Model model) throws BusinessException{	
		model.addAttribute("expenses", administrationService.findAllExpenses());
		return "private/admin/expenses";
	}
	
	@GetMapping(value="/budgets")
	public String budgets (Model model) throws BusinessException{	
		model.addAttribute("budgets", administrationService.findAllAnnualBudgets());
		return "private/admin/budgets";
	}
}
