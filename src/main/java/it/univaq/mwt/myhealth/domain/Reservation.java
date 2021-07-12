package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
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
	
	private LocalDate reservationDate;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToOne
    @JsonBackReference
	private FrontOffice frontOffice;
	
	@OneToOne
	private Visit visit;
	
	@ManyToOne
    @JsonBackReference
	private User patient;
	
	@ManyToOne
    @JsonBackReference
	private Exam exam;
	
	public Reservation() {}
}