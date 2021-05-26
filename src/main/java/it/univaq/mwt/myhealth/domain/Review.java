package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	
//	private Patient patient;
//	private Visit visit;
	
	public Review() {}

}
