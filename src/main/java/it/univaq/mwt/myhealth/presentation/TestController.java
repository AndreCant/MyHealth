package it.univaq.mwt.myhealth.presentation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.RoleDao;
import it.univaq.mwt.myhealth.dao.impl.RoleDaoImpl;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private DocumentService documentService;
	

//	@GetMapping("/test-view")
//	public void all() {
//		User user = new User();
//		user.setUsername("umberto456");
//		user.setEmail("umberto456@test.com");
//		user.setPassword("admin123");
//		try {
//			user.setRole(userService.findRoleByName("doctor"));
//			userService.saveUser(user);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
//	}
	
//	@GetMapping("/addRoles")
//	public void addRoles() {
//		RoleDao roleDao = new RoleDaoImpl();
//		Role role1 = new Role("admin");
//		Role role2 = new Role("doctor");
//		Role role3 = new Role("patient");
//		try {
//			roleDao.save(role1);
//			roleDao.save(role2);
//			roleDao.save(role3);
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
//	}

	@GetMapping("/test-view")
	public void all() {
		User user = new User();
		user.setUsername("admin");
		user.setEmail("admin@test.com");
		user.setPassword((new BCryptPasswordEncoder()).encode("admin123"));
		try {
			user.setRole(userService.findRoleByName("admin"));
			userService.saveUser(user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	@GetMapping(value="/about")
	public String about (){
		return "common/about";
	}
	
	@GetMapping(value="/doctor")
	public String doctorView (){
		return "common/doctor";
	}
	
	@GetMapping(value="/contact")
	public String contactUs (){
		return "common/contactUs";
	}
	
	@GetMapping(value="/login")
	public String login (){
		return "common/login";
	}
	
	@GetMapping(value="/sign-up")
	public String signUp (){
		return "common/signUp";
	}
	
	@GetMapping(value="/listing")
	public String listing (){
		return "common/listings";
	}
	
	@GetMapping(value="/home")
	public String home (){
		return "common/home";
	}
	
	@GetMapping("/connection")
	public void connection() {
//		Role role = new Role();
//		role.setName("patient");
//		try {
//			userService.saveRole(role);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
	}
	
	
	@GetMapping(value="/addListing")
	public String addListing () {
		return "common/admin/dashboardListing";
	}
	
	@GetMapping(value="/dashboardHome")
	public String dashboardHome () {
		return "common/admin/dashboardHome";
	}
	
	@GetMapping(value="/dashboardFavorites")
	public String dashboardFavorites () {
		return "common/admin/dashboardFavorites";
	}
	
	@GetMapping(value="/dashboardMyListings")
	public String dashboardMyListings () {
		return "common/admin/dashboardMyListings";
	}
	
	@GetMapping(value="/myprofile")
	public String myProfile () {
		return "common/admin/myProfile";
	}

	@GetMapping(value="/reviews")
	public String reviews () {
		return "common/admin/dashboardReviews";
	}
	
	@GetMapping(value="/packages")
	public String packages () {
		return "common/admin/dashboardPackages";
	}
	
	@GetMapping(value="/bookings")
	public String bookings () {
		return "common/admin/bookings";
	}
	
	@GetMapping(value="/test")
	public String page () {
		return "page";
	}
	
	@GetMapping(value="/exams")
	public String exams (Model model) throws BusinessException {
		model.addAttribute("exams", examService.findAllExams());	
		return "/common/exams";
	}
	
	@GetMapping(value="/exams/{name}")
	public String exam (Model model, @PathVariable("name") String name) throws BusinessException {
		model.addAttribute("exam", examService.findByName(name));	
		return "/common/blog-single";
	}
	
	@GetMapping(value="/exams/{name}/reservation")
	public String reservation (Model model, @PathVariable("name") String name, HttpServletRequest request) throws BusinessException {
		if ( request.getSession().getAttribute("user") != null) {
			model.addAttribute("exam", examService.findByName(name));
			return "/common/blog-single";
		} else {
			return "/common/signUp";
		}
		
	}
	
}
