package it.univaq.mwt.myhealth.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.ExamService;
import it.univaq.mwt.myhealth.business.FrontOfficeService;
import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.VisitService;
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

@Component
public class DataInitializer {

	@Autowired UserService userService;
	@Autowired ExamService examService;
	@Autowired ReservationService reservationService;
	@Autowired DocumentService documentService;
	@Autowired VisitService visitService;
	@Autowired FrontOfficeService frontOfficeService;
	@Autowired AdministrationService administrationService;
	
	private List<Role> roles;
	private List<Image> imagesUser;
	private List<Image> imagesExam;
	private List<Exam> exams;
	private List<User> users;
	private List<Reservation> reservations;
	private List<Visit> visits;
	private List<Review> reviews;
	private List<Invoice> invoices;
	private List<Payment> payments;
	private List<Paycheck> paychecks;
	private List<Diagnosis> diagnosis;
	private List<Medicine> medicines;
	private List<MedicineDiagnosis> medicineDiagnosis;
	private List<FrontOffice> frontOffices;
	private List<AnnualBudget> annualBudgets;
	private List<Expense> expenses;
	
	@EventListener(ApplicationReadyEvent.class)
	public void initialize() {
		this.initRoles();
		this.initUserImages();
		this.initUsers();
		this.initExams();
		this.initReservations();
		this.initPayments();
		this.initDiagnosis();
		this.initVisits();
		this.initReviews();
		this.initInvoices();
		this.initPaychecks();
		this.initMedicines();
		this.initMedicineDiagnosis();
		this.initFrontOffices();
		this.initAnnualBudgets();
		this.initExpenses();
		this.initExamImages();
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
				ObjectFactory.createAdmin("andrea95", "and@and.it", "admin123", "Andrea", "Cantagallo", 99999, LocalDate.of(1995, 1, 28), "M", "GYZDMH59D63C829U", "Java, Javascript, Excel", this.roles.get(0), this.imagesUser.get(0)),
				ObjectFactory.createDoctor("umberto355", "umb@umb.com", "admin123", "Umberto", "La Barbera", 12345, LocalDate.of(1970, 8, 31), "M", "GYZDMH59D63C829U", "Pediatric Neurology, Neuromuscular medicine", "Neurology", true, this.roles.get(1), this.imagesUser.get(1)),
				ObjectFactory.createDoctor("doctorTest", "umb@umb.com", "admin123", "Umberto", "La Barbera", 12345, LocalDate.of(1970, 8, 31), "M", "GYZDMH59D63C829U", "Pediatric Neurology, Neuromuscular medicine", "Neurology", true, this.roles.get(1), null),
				ObjectFactory.createPatient("lello21", "lol@lol.com", "admin123", "Pippo", "Franco", LocalDate.of(1945, 11, 18), "F", "GYZDMH59D63C829U", true, this.roles.get(2), this.imagesUser.get(2))
			);
			
			userService.saveUsers(this.users);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	private void initExams() {
		try {
			this.exams = List.of(
				ObjectFactory.createExam("AB-01", 1, "exam", "ECG", "cardiology", "ECG", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Neque ornare aenean euismod elementum nisi quis eleifend. Cras semper auctor neque vitae tempus quam. Nibh sed pulvinar proin gravida hendrerit lectus a. Magna ac placerat vestibulum lectus mauris ultrices eros in. Lacus laoreet non curabitur gravida arcu. Sit amet dictum sit amet justo donec.", 38.99),
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
				ObjectFactory.createReservation(LocalDateTime.of(2021, 7, 15, 10, 30), LocalDateTime.of(2021, 7, 15, 11, 30), this.users.get(3), this.exams.get(0)),
				ObjectFactory.createReservation(LocalDateTime.of(2021, 7, 20, 14, 0), LocalDateTime.of(2021, 7, 20, 17, 30), this.users.get(3), this.exams.get(2))
			);
			
			reservationService.saveReservations(this.reservations);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}
	
	public void initPayments() {
		try {
			this.payments = List.of(
				ObjectFactory.createPayment(89.99, 10, false, 22),
				ObjectFactory.createPayment(45.99, 0, true, 4)
			);
			
			reservationService.savePayments(this.payments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initDiagnosis() {
		try {
			this.diagnosis = List.of(
				ObjectFactory.createDiagnosis("AA-00", "Diagnosis Visit 1", "This is diagnosis 1 description!"),
				ObjectFactory.createDiagnosis("AK-47", "Diagnosis Visit 2", "This is diagnosis 2 description!")
			);
			
			visitService.saveDiagnosis(this.diagnosis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initVisits() {
		try {
			this.visits = List.of(
				ObjectFactory.createVisit(LocalDateTime.of(2021, 7, 20, 14, 0), LocalDateTime.of(2021, 7, 20, 14, 30), true, this.reservations.get(0), this.payments.get(0), this.users.get(1), this.diagnosis.get(0)),
				ObjectFactory.createVisit(LocalDateTime.of(2021, 7, 31, 11, 30), LocalDateTime.of(2021, 7, 20, 12, 30), false, this.reservations.get(1), this.payments.get(1), this.users.get(1), this.diagnosis.get(1))
			);
			
			visitService.saveVisits(visits);
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
	
	public void initPaychecks() {
		try {
			List<Paycheck> paychecks = documentService.findAllPaychecks();
			
			if (paychecks == null || paychecks.isEmpty()) {
				this.paychecks = List.of(
					ObjectFactory.createPaycheck("Pippo", "Franco", 12345, "N26-A", 2021, 7, 20, "This is a fake Paycheck!", 4523.58, 38.59)
				);
					
				documentService.savePaychecks(this.paychecks);
			}else {
				this.paychecks = paychecks;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initMedicines() {
		try {
			this.medicines = List.of(
				ObjectFactory.createMedicine("DFL", "Deflan", "Deflazacort", "Medicine Description!!!", 30.0),
				ObjectFactory.createMedicine("ARM", "Armisarte", "Pemetrexed", "Medicine Description!!!", 25.0),
				ObjectFactory.createMedicine("VNL", "Venolen", "Troxerutina", "Medicine Description!!!", 300.0),
				ObjectFactory.createMedicine("DFR", "Dificlir", "Fidaxomicina", "Medicine Description!!!", 200.0)
			);
			
			visitService.saveMedicines(this.medicines);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initMedicineDiagnosis() {
		try {
			this.medicineDiagnosis = List.of(
				ObjectFactory.createMedicineDiagnosis(this.medicines.get(0), this.diagnosis.get(0)),
				ObjectFactory.createMedicineDiagnosis(this.medicines.get(1), this.diagnosis.get(1)),
				ObjectFactory.createMedicineDiagnosis(this.medicines.get(3), this.diagnosis.get(1))
			);
			
			visitService.saveMedicineDiagnosis(this.medicineDiagnosis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initFrontOffices() {
		try {
			this.frontOffices = List.of(
				ObjectFactory.createFrontOffice("Office 1", 1),
				ObjectFactory.createFrontOffice("Office 2", 2),
				ObjectFactory.createFrontOffice("Office 3", 3),
				ObjectFactory.createFrontOffice("Office 4", 4),
				ObjectFactory.createFrontOffice("Office 5", 5)
			);
			
			frontOfficeService.saveFrontOffices(this.frontOffices);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initAnnualBudgets() {
		try {
			this.annualBudgets = List.of(
				ObjectFactory.createAnnualBudget(895263.59, 7859256.55, 100000.0, 2021, false)
			);
			
			administrationService.saveAnnualBudgets(this.annualBudgets);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initExpenses() {
		try {
			this.expenses = List.of(
				ObjectFactory.createExpense("OM-01", "Ordinary maintenance", "Expence description", 4956.55, this.frontOffices.get(0), this.annualBudgets.get(0))
			);
			
			administrationService.saveExpenses(this.expenses);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initUserImages() {
		try {
			this.imagesUser = List.of(
				ObjectFactory.createImage("Admin img", "/dist/img/instagram-4.jpg", null),
				ObjectFactory.createImage("Doctor img", "/dist/img/instagram-2.jpg", null),
				ObjectFactory.createImage("Patient img", "/dist/img/instagram-3.jpg", null)
			);
			
			examService.saveImages(this.imagesUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initExamImages() {
		try {
			this.imagesExam = List.of(
				ObjectFactory.createImage("Test 1", "https://neelimahospitals.com/wp-content/uploads/2018/02/Cardiology_image.jpg", this.exams.get(0))
			);
			
			examService.saveImages(this.imagesExam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
