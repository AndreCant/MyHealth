package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	
	@OneToMany(mappedBy = "diagnosis")
    @JsonManagedReference
	private List<MedicineDiagnosis> medicineDiagnosis;
	
	@OneToMany(mappedBy = "diagnosis")
    @JsonManagedReference
	private List<Visit> visits;
	
	public Diagnosis() {}
}
