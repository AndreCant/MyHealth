package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private LocalDate reservationDate;
	private String description;
	private String specialization;
	private String subSpecialization;
	private LocalDate startDate;
	private LocalDate endDate;
	private int session;
	private String type;
	private String name;

	@ManyToOne
    @JsonBackReference
	private FrontOffice frontOffice;
	
	@OneToOne
	private Visit visit;
	
	@ManyToOne
    @JsonBackReference
	private User patient;
	
	public Reservation() {}
}
