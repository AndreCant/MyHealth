package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String body;
	private int vote;
	private boolean isRemoved;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "review")
	private Visit visit;
	
	@ManyToOne
    @JsonManagedReference 
	private User patient;
	
	public Review() {}

}
