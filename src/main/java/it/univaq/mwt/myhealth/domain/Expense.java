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
@Table(name = "expense")
public class Expense extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String description;
	private Double total;
	private String paycheck; //to verify, external id to mongo
	
//	private FrontOffice frontOffice;
//	private AnnualBudget annualBudget;
	
	public Expense() {}
}
