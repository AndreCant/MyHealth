package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	private LocalDate dateOfBirth;
	private String type;
	private boolean hasVisitToComplete;
	private int register;
	private String specialization;
	private String skills;
	private Blob curriculum;
	
	//@OneToOne
    //@JsonBackReference
    //private PatientConvention patientConvention;
	
	public User() {}
	
	
}
