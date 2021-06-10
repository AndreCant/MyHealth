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
@Table(name = "medicinediagnosis")
public class MedicineDiagnosis extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JsonBackReference
    private Medicine medicine;
	
	@ManyToOne
    @JsonBackReference
    private Diagnosis diagnosis;
		
	public MedicineDiagnosis() {}
}
