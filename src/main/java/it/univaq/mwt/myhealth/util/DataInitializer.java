package it.univaq.mwt.myhealth.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;

@Component
public class DataInitializer {

	@Autowired UserService userService;
	@Autowired ExamService examService;
	@Autowired ReservationService reservationService;
	@Autowired DocumentService documentService;
	
	private List<Role> roles;
	private List<Exam> exams;
	private List<User> users;
	private List<Reservation> reservations;
	private List<Visit> visits;
	private List<Review> reviews;
	private List<Invoice> invoices;
	
	@EventListener(ApplicationReadyEvent.class)
	public void initialize() {
		this.initRoles();
		this.initUsers();
		this.initExams();
		this.initReservations();
		this.initVisits();
		this.initReviews();
		this.initInvoices();
	}
	
	private void initRoles() {
		try {
			this.roles = List.of(
				ObjectFactory.createRole("admin", "System Administrator"),
				ObjectFactory.createRole("doctor", "Doctor User"),
				ObjectFactory.createRole("patient", "Patient User")
			);
			
			userService.saveRoles(this.roles);
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
			
			userService.saveUsers(this.users);
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
			
			examService.saveExams(this.exams);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initReservations() {
		try {
			this.reservations = List.of(
				ObjectFactory.createReservation(LocalDateTime.of(2021, 7, 15, 10, 30), LocalDateTime.of(2021, 7, 15, 11, 30), this.users.get(2), this.exams.get(0)),
				ObjectFactory.createReservation(LocalDateTime.of(2021, 7, 20, 14, 0), LocalDateTime.of(2021, 7, 20, 17, 30), this.users.get(2), this.exams.get(2))
			);
			
			reservationService.saveReservations(this.reservations);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initVisits() {
		try {
			this.visits = List.of(
				ObjectFactory.createVisit(LocalDateTime.of(2021, 7, 20, 14, 0), LocalDateTime.of(2021, 7, 20, 14, 30), true, this.reservations.get(0), null, this.users.get(1)),
				ObjectFactory.createVisit(LocalDateTime.of(2021, 7, 31, 11, 30), LocalDateTime.of(2021, 7, 20, 12, 30), false, this.reservations.get(1), null, this.users.get(1))
			);
			
			reservationService.saveVisits(visits);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initReviews() {
		try {
			this.reviews = List.of(
				ObjectFactory.createReview("Good!!!", "This is a review body bla bla bla hello world.", 4, this.visits.get(0), this.users.get(2))
			);
			
			reservationService.saveReviews(reviews);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initInvoices() {
		try {
			List<Invoice> invoices = documentService.findAllInvoices();
			
			if (invoices == null || invoices.isEmpty()) {
				this.invoices = List.of(
					ObjectFactory.createInvoice(4, 22, 4532, 35.88)
				);
					
				documentService.saveInvoices(this.invoices);
			}else {
				this.invoices = invoices;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
