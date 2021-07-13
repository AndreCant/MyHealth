package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
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
@Table(name = "exam")
public class Exam extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private int session;
	private String type;
	private String name;
	private String specialization;
	private String subSpecialization;
	private String description;
	private Double price;
	
	@OneToMany(mappedBy = "exam")
    @JsonManagedReference
    private List<Reservation> reservations;
}
