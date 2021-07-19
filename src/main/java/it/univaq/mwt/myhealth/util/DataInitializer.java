package it.univaq.mwt.myhealth.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

@Component
public class DataInitializer {

	@Autowired
	UserService userService;
	
	@Autowired
	ExamService examService;
	
	@Autowired
	ReservationService reservationService;
	
	private List<Role> roles;
	private List<Exam> exams;
	private List<User> users;
	
	@EventListener(ApplicationReadyEvent.class)
	public void initialize() {
		this.initRoles();
		this.initUsers();
		this.initExams();
		this.initReservations();
	}
	
	private void initRoles() {
		try {
			this.roles = List.of(
				ObjectFactory.createRole("admin", "System Administrator"),
				ObjectFactory.createRole("doctor", "Doctor User"),
				ObjectFactory.createRole("patient", "Patient User")
			);
			
			userService.saveRoles(new ArrayList<Role>(this.roles));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initUsers() {
		try {
			this.users = List.of(
				ObjectFactory.createUser("andrea95", "and@and.it", "admin123", this.roles.get(0)),
				ObjectFactory.createUser("umberto355", "umb@umb.com", "admin123", this.roles.get(1)),
				ObjectFactory.createUser("lello21", "lol@lol.com", "admin123", this.roles.get(2))
			);
			
			userService.saveUsers(new ArrayList<User>(this.users));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initExams() {
		try {
			this.exams = List.of(
				ObjectFactory.createExam("AB-01", 1, "exam", "ECG", "cardiology", "ECG", "", 38.99),
				ObjectFactory.createExam("CG-31", 10, "rehabilitation path", "scoliosis", "physiotherapy", "scoliosis", "", 989.99),
				ObjectFactory.createExam("OO-94", 1, "exam", "", "eye", "gradation", "", 70.00)
			);
			
			examService.saveExams(new ArrayList<Exam>(this.exams));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initReservations() {
		try {
			reservationService.saveReservations(new ArrayList<Reservation>(List.of(
				ObjectFactory.createReservation(LocalDate.of(2021, 7, 15), LocalDate.of(2021, 7, 15), this.users.get(2), this.exams.get(0)),
				ObjectFactory.createReservation(LocalDate.of(2021, 7, 20), LocalDate.of(2021, 7, 20), this.users.get(2), this.exams.get(2))
			)));
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
}
