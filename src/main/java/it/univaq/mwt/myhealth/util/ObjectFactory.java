package it.univaq.mwt.myhealth.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.impl.UserServiceImpl;
import it.univaq.mwt.myhealth.domain.AnnualBudget;
import it.univaq.mwt.myhealth.domain.Diagnosis;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Expense;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Image;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.MedicineDiagnosis;
import it.univaq.mwt.myhealth.domain.Paycheck;
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
	
	private static User createUser(String username, String email, String password, Role role, String name, String surname, LocalDate dateOfBirth, String gender, String fiscalCode) {
		String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
		User user = new User(username, email, encodedPassword, role);
		user.setName(name);
		user.setSurname(surname);
		user.setDateOfBirth(dateOfBirth);
		user.setGender(gender);
		user.setFiscalCode(fiscalCode);
		return user;
	}
	
	public static User createAdmin(String username, String email, String password, String name, String surname, int register, LocalDate dateOfBirth, String gender, String fiscalCode, String skills, Role role) throws BusinessException {
		User admin = createUser(username, email, password, role, name, surname, dateOfBirth, gender, fiscalCode);
		admin.setRegister(register);
		admin.setSkills(skills);
		return admin;
	}
	
	public static User createDoctor(String username, String email, String password, String name, String surname, int register, LocalDate dateOfBirth, String gender, String fiscalCode, String skills, String specialization, boolean hasVisitToComplete, Role role) throws BusinessException {
		User doctor = createUser(username, email, password, role, name, surname, dateOfBirth, gender, fiscalCode);
		doctor.setRegister(register);
		doctor.setSkills(skills);
		doctor.setSpecialization(specialization);
		doctor.setHasVisitToComplete(hasVisitToComplete);
		return doctor;
	}
	
	public static User createPatient(String username, String email, String password, String name, String surname, LocalDate dateOfBirth, String gender, String fiscalCode, boolean hasVisitToComplete, Role role) throws BusinessException {
		User patient = createUser(username, email, password, role, name, surname, dateOfBirth, gender, fiscalCode);
		patient.setHasVisitToComplete(hasVisitToComplete);
		return patient;
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
	
	public static Payment createPayment(Double price, int discount, boolean isPaid, int tax) {
		Double totDiscount = discount > 0 ? (price / 100) * discount : 0;
		Double totTax = (price / 100) * tax;
		
		Payment payment = new Payment();
		payment.setPrice(price);
		payment.setDiscount(discount);
		payment.setPaid(isPaid);
		payment.setTax(tax);
		payment.setFinalPrice(price - totDiscount + totTax);
		return payment;
	}
	
	public static Visit createVisit(LocalDateTime startHour, LocalDateTime endHour, boolean isCompleted, Reservation reservation, Payment payment, User doctor, Diagnosis diagnosis) {
		Visit visit = new Visit();
		visit.setStartHour(startHour);
		visit.setEndHour(endHour);
		visit.setCompleted(isCompleted);
		visit.setDoctor(doctor);
		visit.setPayment(payment);
		visit.setReservation(reservation);
		visit.setDiagnosis(diagnosis);
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
		invoice.setFinalPrice(BigDecimal.valueOf(totalPrice + totalTax).setScale(2, RoundingMode.HALF_UP).doubleValue());
		return invoice;
	}
	
	public static Paycheck createPaycheck(String name, String surname, Integer register, String code, Integer year, Integer month, int days, String description, Double grossSalary, Double totalTax) {
		Double tax = (grossSalary / 100) * totalTax;
		
		Paycheck paycheck = new Paycheck();
		paycheck.setName(name);
		paycheck.setSurname(surname);
		paycheck.setRegister(register);
		paycheck.setCode(code);
		paycheck.setYear(year);
		paycheck.setMonth(month);
		paycheck.setDays(days);
		paycheck.setDescription(description);
		paycheck.setGrossSalary(grossSalary);
		paycheck.setTotalTax(totalTax);
		paycheck.setNetSalary(BigDecimal.valueOf(grossSalary - tax).setScale(2, RoundingMode.HALF_UP).doubleValue());
		return paycheck;
	}
	
	public static Diagnosis createDiagnosis(String code, String title, String description) {
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setCode(code);
		diagnosis.setTitle(title);
		diagnosis.setDescription(description);
		return diagnosis;
	}
	
	public static Medicine createMedicine(String code, String name, String activePrinciple, String description, Double weight) {
		Medicine medicine = new Medicine();
		medicine.setName(name);
		medicine.setCode(code);
		medicine.setActivePrinciple(activePrinciple);
		medicine.setDescription(description);
		medicine.setWeight(weight);
		return medicine;
	}
	
	public static MedicineDiagnosis createMedicineDiagnosis(Medicine medicine, Diagnosis diagnosis) {
		MedicineDiagnosis medicineDiagnosis = new MedicineDiagnosis();
		medicineDiagnosis.setMedicine(medicine);
		medicineDiagnosis.setDiagnosis(diagnosis);
		return medicineDiagnosis;
	}
	
	public static FrontOffice createFrontOffice(String name, int number) {
		FrontOffice frontOffice = new FrontOffice();
		frontOffice.setName(name);
		frontOffice.setNumber(number);
		return frontOffice;
	}
	
	public static AnnualBudget createAnnualBudget(Double totalInvoices, Double totalExpenses, Double budget, Integer year, boolean isAtLoss) {
		AnnualBudget annualBudget = new AnnualBudget();
		annualBudget.setTotalInvoices(totalInvoices);
		annualBudget.setTotalExpenses(totalExpenses);
		annualBudget.setBudget(budget);
		annualBudget.setYear(year);
		annualBudget.setAtLoss(isAtLoss);
		return annualBudget;
	}
	
	public static Expense createExpense(String code, String name, String description, Double total, FrontOffice frontOffice, AnnualBudget annualBudget) {
		Expense expense = new Expense();
		expense.setCode(code);
		expense.setName(name);
		expense.setDescription(description);
		expense.setTotal(total);
		expense.setFrontOffice(frontOffice);
		expense.setAnnualBudget(annualBudget);
		return expense;
	}
	
	public static Image createImage(String name, String url, Exam exam) {
		Image image = new Image();
		image.setName(name);
		image.setUrl(url);
		image.setExam(exam);
		return image;
	}
}
