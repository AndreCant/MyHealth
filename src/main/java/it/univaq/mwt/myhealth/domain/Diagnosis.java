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
@Table(name = "diagnosis")
public class Diagnosis extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String title;
	private String description;
	
//	private Visit visit;
//	private MedicineDiagnosis medicineDiagnosis;
	
	public Diagnosis() {}
}
