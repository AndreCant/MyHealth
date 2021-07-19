package it.univaq.mwt.myhealth.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

public class ObjectFactory {
	
	private ObjectFactory() {}
	
	public static Role createRole(String name, String description) {
		return new Role(name, description);
	}
	
	public static User createUser(String username, String email, String password, Role role) {
		String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
		return new User(username, email, encodedPassword, role);
	}
	
	public static Exam createExam(String code, int session, String type, String name, String specialization, String subSpecialization, String description, Double price) {
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
	
	public static Reservation createReservation(LocalDate startDate, LocalDate endDate, User patient, Exam exam) {
		Reservation reservation = new Reservation();
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setPatient(patient);
		reservation.setExam(exam);
		reservation.setReservationDate(LocalDate.now());
		return reservation;
	}
}
