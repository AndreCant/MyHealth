package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "convention")
public class Convention extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String country;
	private int refundPercentage;
		
	@ManyToOne
    @JsonBackReference
    private Payment payment;
	
	@OneToMany(mappedBy = "convention")
    @JsonManagedReference
	private List<PatientConvention> patientConvention;
	
	public Convention() {}
}
