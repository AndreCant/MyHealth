package it.univaq.mwt.myhealth.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.rest.dto.LoginDto;
import it.univaq.mwt.myhealth.rest.dto.RegistrationDto;
import it.univaq.mwt.myhealth.rest.jwt.JwtTokenUtil;
import it.univaq.mwt.myhealth.util.ObjectFactory;

@RestController
@RequestMapping("rest/public")
public class PublicController {
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private JwtTokenUtil jwtTokenUtil;
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private UserService userService;
	@Autowired private ExamService examService;

	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            if (auth.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("username", loginDto.getUsername());
                responseMap.put("message", "Login OK");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
            	System.out.println("else");
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "User is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (UsernameNotFoundException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Username not found");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
	}
	
	@PostMapping(value = "registration")
	public ResponseEntity<?> registration(@RequestBody RegistrationDto registrationDto) {
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			if(registrationDto.getUsername() == null || registrationDto.getUsername().isBlank() || userService.existsByUsername(registrationDto.getUsername())) {
				responseMap.put("error", true);
                responseMap.put("message", "Username alredy exist or blank!");
                return ResponseEntity.status(401).body(responseMap);
			}
			if(registrationDto.getEmail() == null || registrationDto.getEmail().isBlank() || userService.existsByEmail(registrationDto.getEmail())) {
				responseMap.put("error", true);
                responseMap.put("message", "Email alredy exist or blank!");
                return ResponseEntity.status(401).body(responseMap);
			}
			if(registrationDto.getFiscalCode() == null || registrationDto.getFiscalCode().isBlank() || userService.existsByFiscalCode(registrationDto.getFiscalCode())) {
				responseMap.put("error", true);
                responseMap.put("message", "Fiscal Code alredy exist or blank!");
                return ResponseEntity.status(401).body(responseMap);
			}
			
			User user = ObjectFactory.createPatient(
							registrationDto.getUsername(), 
							registrationDto.getEmail(),
							registrationDto.getPassword(), 
							registrationDto.getName(), 
							registrationDto.getSurname(), 
							registrationDto.getDateOfBirth(), 
							registrationDto.getGender(), 
							registrationDto.getFiscalCode(), 
							false, 
							userService.findRoleByName("patient"),
							null
						);
			
			userService.saveUser(user);
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			
	        String token = jwtTokenUtil.generateToken(userDetails);
            responseMap.put("error", false);
            responseMap.put("username", user.getUsername());
            responseMap.put("message", "Registration OK");
            responseMap.put("token", token);
            return ResponseEntity.ok(responseMap);
			
		} catch (ConstraintViolationException cve) {
		    for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
		    	responseMap.put("error", true);
		    	responseMap.put("value", cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString());
		    	responseMap.put("constraint", cv.getMessage());
		    }
            return ResponseEntity.status(400).body(responseMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
		}
		
	}
	
	@GetMapping("exams")
	public ResponseEntity<?> getExams(@Nullable @RequestParam("type") String type){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			List<Exam> exams = new ArrayList<Exam>();
			String typeLabel;
			
			if(type == null) {
				exams = examService.findAllExams();
				typeLabel = "all";
			}else if(type.equals("exam")) {
				exams = examService.findExamsByType("exam");
				typeLabel = "exams";
			}else if(type.equals("path")) {
				exams = examService.findExamsByType("rehabilitation path");
				typeLabel = "rehabilitationPaths";
			}else {
				responseMap.put("error", true);
	            responseMap.put("message", "Invalid type");
	            return ResponseEntity.status(400).body(responseMap);
			}
			
			for (Exam exam : exams) exam.setReservations(null);
			
			responseMap.put(typeLabel, exams);
			return ResponseEntity.ok(responseMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
		}
	}
	

}
