package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Table;

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

//	private Patient patient;
//	private FrontOffice frontOffice;
//	private Visit visit;
	
	public Reservation() {}
}
