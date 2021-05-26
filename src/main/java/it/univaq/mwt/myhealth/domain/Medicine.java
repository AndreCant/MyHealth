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
@Table(name = "medicine")
public class Medicine extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String activePrinciple;
	private String description;
	private Double weight;
	
//	private MedicineDiagnosis medicineDiagnosis;
	
	public Medicine() {}
}
