package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "annualbudget")
public class AnnualBudget extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Double totalInvoices;
	private Double totalExpenses;
	private Double budget;
	private Year year;
	private boolean isAtLoss;
	
	public AnnualBudget() {}
}
