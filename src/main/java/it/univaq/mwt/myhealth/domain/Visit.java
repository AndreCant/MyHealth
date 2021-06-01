package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "visit")
public class Visit extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime startHour;
	private LocalDateTime endHour;
	private boolean isCompleted;
		
	@ManyToOne
    @JsonBackReference
    private Diagnosis diagnosis;
		
	@OneToOne
	private Reservation reservation;

	@OneToOne
	private Review review;
	
	@OneToOne
	private Payment payment;
	
	@ManyToOne
    @JsonBackReference
	private User doctor;
	
	public Visit() {}

}
