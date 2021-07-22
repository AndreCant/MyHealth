package it.univaq.mwt.myhealth.domain;

import java.io.Serializable;
import java.time.Year;
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
@Table(name = "annualbudget")
public class AnnualBudget extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Double totalInvoices;
	private Double totalExpenses;
	private Double budget;
	private Integer year;
	private boolean isAtLoss;
	
	@OneToMany(mappedBy = "annualBudget")
    @JsonManagedReference
	private List<Expense> expenses;
	
	public AnnualBudget() {}
}
