package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patientconvention")
public class PatientConvention extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JsonBackReference
	private Convention convention;
	
	@ManyToOne
    @JsonBackReference
	private User patient;
	
	public PatientConvention() {}
}
