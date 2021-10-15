package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

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
import javax.validation.constraints.Null;

import org.springframework.lang.Nullable;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visit")
public class Visit extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")	
	private LocalDateTime startHour;
	private LocalDateTime endHour;
	private boolean isCompleted;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "payment_id")
    private Payment payment;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "review_id")
    private Review review;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "visit")
    private Reservation reservation;
	
	@ManyToOne
    @JsonBackReference
	private User doctor;
	
	public Visit() {}

}
