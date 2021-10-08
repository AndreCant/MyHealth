package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
		
	@OneToOne(cascade = CascadeType.ALL)
	private Visit visit;
		
	@ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference 
	private User patient;
	
	public Review() {}

}
