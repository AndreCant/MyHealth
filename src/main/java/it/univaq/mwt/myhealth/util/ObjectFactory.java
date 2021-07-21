package it.univaq.mwt.myhealth.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Payment;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;
import it.univaq.mwt.myhealth.domain.Visit;

public class ObjectFactory {
	
	private ObjectFactory() {}
	
	public static Role createRole(String name, String description) {
		return new Role(name, description);
	}
	
	public static User createUser(String username, String email, String password, Role role) {
		String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
		return new User(username, email, encodedPassword, role);
	}
	
	public static Exam createExam( String code, int session, String type, String name, String specialization, String subSpecialization, String description, Double price) {
		Exam exam = new Exam();
		exam.setCode(code);
		exam.setDescription(description);
		exam.setName(name);
		exam.setPrice(price);
		exam.setSession(session);
		exam.setSpecialization(specialization);
		exam.setSubSpecialization(subSpecialization);
		exam.setType(type);
		return exam;
	}
	
	public static Reservation createReservation(LocalDateTime startHour, LocalDateTime endHour, User patient, Exam exam) {
		Reservation reservation = new Reservation();
		reservation.setStartHour(startHour);
		reservation.setEndHour(endHour);
		reservation.setPatient(patient);
		reservation.setExam(exam);
		reservation.setReservationDate(LocalDate.now());
		return reservation;
	}
	
	public static Visit createVisit(LocalDateTime startHour, LocalDateTime endHour, Boolean isCompleted, Reservation reservation, Payment payment, User doctor) {
		Visit visit = new Visit();
		visit.setStartHour(startHour);
		visit.setEndHour(endHour);
		visit.setCompleted(isCompleted);
		visit.setDoctor(doctor);
		visit.setPayment(payment);
		visit.setReservation(reservation);
		return visit;
	}
	
	public static Review createReview(String title, String body, int vote, Visit visit, User patient) {
		Review review = new Review();
		review.setTitle(title);
		review.setBody(body);
		review.setVote(vote);
		review.setVisit(visit);
		review.setPatient(patient);
		return review;
	}
	
	public static Invoice createInvoice(int numbPayments, int tax, int code, Double totalPrice) {
		Double totalTax = (totalPrice / 100) * tax;
		
		Invoice invoice = new Invoice();
		invoice.setNumbPayments(numbPayments);
		invoice.setTax(tax);
		invoice.setCode(code);
		invoice.setTotalPrice(totalPrice);
		invoice.setIssueDate(LocalDate.now());
		invoice.setFinalPrice(totalPrice + totalTax);
		return invoice;
	}
}
