package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@ManyToOne
    @JsonBackReference
    private FrontOffice frontOffice;
	
	@ManyToOne
    @JsonBackReference
    private AnnualBudget annualBudget;
	
}
