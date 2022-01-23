package it.univaq.mwt.myhealth.rest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.rest.dto.LoginDto;
import it.univaq.mwt.myhealth.rest.dto.RegistrationDto;
import it.univaq.mwt.myhealth.rest.jwt.JwtTokenUtil;
import it.univaq.mwt.myhealth.util.ObjectFactory;

@RestController
@RequestMapping("rest/public")
public class PublicController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserService userService;

	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
		return this.auth(loginDto.getUsername(), loginDto.getPassword(), "Login OK");
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
	
	private ResponseEntity<?> auth(String username, String password, String message){
		Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("username", username);
                responseMap.put("message", message);
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

}
