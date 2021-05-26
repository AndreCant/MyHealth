package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visit")
public class Visit extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime startHour;
	private LocalDateTime endHour;
	private boolean isCompleted;
	
//	private Review review;
//	private Reservation reservation;
//	private Diagnosis diagnosis;
//	private Payment payment;
//	private User doctor;
	
	public Visit() {}

}
