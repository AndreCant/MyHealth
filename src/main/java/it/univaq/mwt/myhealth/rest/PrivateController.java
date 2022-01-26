package it.univaq.mwt.myhealth.rest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.ReviewService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.business.auth.UserDetailsImpl;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;
import it.univaq.mwt.myhealth.rest.dto.RegistrationDto;
import it.univaq.mwt.myhealth.rest.dto.ReviewDto;
import it.univaq.mwt.myhealth.util.Utility;

@RestController
@RequestMapping("rest/private")
public class PrivateController {
	
	@Autowired private UserService userService;
	@Autowired private ReservationService reservationService;
	@Autowired private ExamService examService;
	@Autowired private AdministrationService administrationService;
	@Autowired private VisitService visitService;
	@Autowired private ReviewService reviewService;
	
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
	
	@PostMapping(value="exam/{id}/reservation")
	public ResponseEntity<?> createReservation(@PathVariable("id") Long id, @RequestParam("day") String day, @RequestParam("hour") String hour){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			LocalDate localDay = LocalDate.parse((CharSequence)day);
			LocalTime localHour = LocalTime.parse((CharSequence)hour);
			LocalDateTime date = LocalDateTime.of(localDay, localHour);
			
			Exam exam = examService.findById(id);
			    
		    if(exam != null) {
		    	List<Reservation> storedReservations = reservationService.findReservationByExam(id);
			    List<LocalDateTime> update_l = new ArrayList<>();
			   
			    for (Reservation res : storedReservations) update_l.add(res.getStartHour());
			    
			    if(!update_l.contains(date)) {
			    	Reservation reservation = new Reservation();
			    	Visit visit = new Visit();
		  		    reservation.setPatient(((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
		  		    reservation.setExam(exam);
		  		    reservation.setFrontOffice(administrationService.findFrontOfficeById((long)Utility.getRandomNumberInRange(1, 5)));
		  		    reservation.setStartHour(date);
		  		    reservation.setEndHour(date.plusMinutes(30));
		  		    reservation.setReservationDate(date.toLocalDate());
		  		    
		  		    visit.setReservation(reservation);
		  		    visit.setDoctor(userService.findRandomDoctor(Utility.getRandomNumberInRange(2, 3)));
		  		    reservation.setVisit(visit);
		  		    
		  		    visitService.saveVisit(visit);
		  			reservationService.save(reservation);  	
		  			
		  			responseMap.put("message", "Ok!");
					return ResponseEntity.status(201).body(responseMap);
				}else {
					responseMap.put("error", true);
					responseMap.put("message", "Time not available!");
					return ResponseEntity.status(400).body(responseMap);
				}
			   
		    }else {
		    	responseMap.put("error", true);
				responseMap.put("message", "Not Found");
				return ResponseEntity.status(404).body(responseMap);
		    }
		}catch(BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
	
	@GetMapping(value="reservations")
	public ResponseEntity<?> getReservations(){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			List<Reservation> reservations = reservationService.findReservationsByPatient(((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
			for (Reservation reservation : reservations) reservation.setVisit(null);
			responseMap.put("reservations", reservations);
			return ResponseEntity.ok(responseMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
	
	@DeleteMapping(value="reservation/{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable("id") Long id){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			Reservation reservation = reservationService.findReservationById(id);
			
			if(reservation != null) {
				reservationService.delete(id);
				return ResponseEntity.status(204).body(responseMap);
			}else {
				responseMap.put("error", true);
				responseMap.put("message", "Not Found");
				return ResponseEntity.status(404).body(responseMap);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
	
	@GetMapping(value="visits")
	public ResponseEntity<?> getVisits(){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			List<Visit> visits = visitService.findByPatient(((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());
			for (Visit visit : visits) {
				visit.setReservation(null);
				visit.setReview(null);
				visit.setPayment(null);
			}
			responseMap.put("visits", visits);
			return ResponseEntity.ok(responseMap);
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
		
		
	}
	
	@PostMapping(value="visit/{id}/review")
	public ResponseEntity<?> createReview(@PathVariable("id") Long id, @RequestBody ReviewDto reviewDto){
		Map<String, Object> responseMap = new HashMap<>();
		
		try {
			Visit visit = visitService.findById(id);
			
			if (visit != null) {
				Review review = new Review();
				review.setTitle(reviewDto.getTitle());
				review.setBody(reviewDto.getBody());
				review.setVote(reviewDto.getVote());
				review.setPatient(((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
				visit.setReview(review);
				reviewService.save(review);
				
				responseMap.put("message", "Ok!");
				return ResponseEntity.ok(responseMap);
			}else {
				responseMap.put("error", true);
				responseMap.put("message", "Not Found");
				return ResponseEntity.status(404).body(responseMap);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			responseMap.put("error", true);
			responseMap.put("message", e.getMessage());
			return ResponseEntity.status(500).body(responseMap);
		}
	}
}
