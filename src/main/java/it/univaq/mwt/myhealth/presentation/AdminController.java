package it.univaq.mwt.myhealth.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.Image;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.util.ObjectFactory;
import it.univaq.mwt.myhealth.util.Utility;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExamService examService;
	
	@GetMapping(value="/profile")
	public String profile (Model model) throws BusinessException
	{	
		String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findUserByUsername(currentUserName));
		return "private/admin/myProfile"; 
	}
	
	@GetMapping(value="/users")
	public String users (Model model) throws BusinessException{	
		model.addAttribute("users", userService.findAllUsers());
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
		currentUser.setEmail(user.getEmail());
		currentUser.setSpecialization(user.getSpecialization());
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setFiscalCode(user.getFiscalCode());
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
		System.out.println("userId: " + userId);
        if (file.isEmpty()) {
//            attributes.addFlashAttribute("message", "Please select a file to upload.");
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


        // return success response
//        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/admin/user/update?id=" + userId;
    }
}
