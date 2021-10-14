package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate reservationDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")	
	private LocalDateTime startHour;
	private LocalDateTime endHour;
	
	@ManyToOne
    @JsonBackReference
	private FrontOffice frontOffice;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "visit_id")
    private Visit visit;
	
	@ManyToOne
    @JsonBackReference
	private User patient;
	
	@ManyToOne
    @JsonBackReference
	private Exam exam;
	
	public Reservation() {}
	

}