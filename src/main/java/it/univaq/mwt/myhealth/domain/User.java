package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.univaq.mwt.myhealth.annotation.EmailUnique;
import it.univaq.mwt.myhealth.annotation.FiscalCode;
import it.univaq.mwt.myhealth.annotation.FiscalCodeUnique;
import it.univaq.mwt.myhealth.annotation.UsernameUnique;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@UsernameUnique
	@Size(min = 3, max = 25)
	@Column(unique = true)
	private String username;
	
	@Email
	@EmailUnique
	@Size(min = 3, max = 45)
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Size(min = 3, max = 25)
	private String name;
	
	@Size(min = 3, max = 25)
	private String surname;
	
	@FiscalCode
	@FiscalCodeUnique
	@Size(min = 3, max = 25)
	@Column(unique = true)
	private String fiscalCode;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	private boolean hasVisitToComplete;
	private int register;
	private String specialization;
	private String skills;
	private String gender;
	
	@ManyToOne
    @JsonBackReference
	private Role role;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> reviews;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Visit> visits;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "image_id")
	private Image image;
	
	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	public String getFullName() {
		return this.name + " " + this.surname;
	}
	
	public int getAge() {
		return this.dateOfBirth != null ? Period.between(this.dateOfBirth, LocalDate.now()).getYears() : 0;
	}

}
