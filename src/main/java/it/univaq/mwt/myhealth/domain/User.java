package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	private String username;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String fiscalCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private boolean hasVisitToComplete;
	private int register;
	private String specialization;
	private String skills;
	private Blob curriculum;
	private String gender;
	
	@ManyToOne
    @JsonBackReference
	private Role role;
	
	@OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "patient")
    @JsonManagedReference
    private List<Review> reviews;
	
	@OneToMany(mappedBy = "doctor")
    @JsonManagedReference
    private List<Visit> visits;
	
	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

}
