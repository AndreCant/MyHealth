package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "review")
    private Visit visit;
	
	@OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL)
    @JsonManagedReference
	private List<MedicineDiagnosis> medicineDiagnosis;
}
