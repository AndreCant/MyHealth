package it.univaq.mwt.myhealth.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.rest.dto.RegistrationDto;

@RestController
@RequestMapping("rest/private")
public class PrivateController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping(value = "profile")
	public ResponseEntity<?> getProfile() {
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			responseMap.put("user", userService.findUserByUsername(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
			return ResponseEntity.status(200).body(responseMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
	
	@PutMapping(value = "profile")
	public ResponseEntity<?> setProfile(@RequestBody RegistrationDto userDto){
		Map<String, Object> responseMap = new HashMap<>();
		
		User user;
		try {
			user = userService.findUserByUsername(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
			
			if (user != null) {
				user.setName(userDto.getName());
				user.setSurname(userDto.getSurname());
				user.setFiscalCode(userDto.getFiscalCode());
				user.setDateOfBirth(userDto.getDateOfBirth());
				user.setGender(userDto.getGender());
				
				userService.updateUser(user);
				return ResponseEntity.status(204).body(responseMap);
			}else {
				responseMap.put("error", true);
				responseMap.put("message", "User not found");
				return ResponseEntity.status(404).body(responseMap);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
	
	@PostMapping(value="exam/{id}")
	public ResponseEntity<?> createReservation(@PathVariable("id") String id, @RequestParam("date") LocalDate date){
		return null;
	}
	
	@GetMapping(value="reservations")
	public ResponseEntity<?> getReservations(){
		return null;
	}
	
	@DeleteMapping(value="reservation/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable("id") String id){
		return null;
	}
	
	@GetMapping(value="visits")
	public ResponseEntity<?> getVisits(){
		return null;
	}
	
	@PostMapping(value="visit/{id}/review")
	public ResponseEntity<?> createReview(@PathVariable("id") String id){
		return null;
	}
	
	
	
}
