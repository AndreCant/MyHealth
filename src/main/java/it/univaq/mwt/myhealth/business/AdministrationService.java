package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.AnnualBudget;
import it.univaq.mwt.myhealth.domain.Expense;
import it.univaq.mwt.myhealth.domain.Payment;

public interface AdministrationService {
	
	void saveExpenses(List<Expense> expenses) throws BusinessException;
	
	void saveAnnualBudgets(List<AnnualBudget> annualBudgets) throws BusinessException;
	
	List<Payment> findAllPayments() throws BusinessException;
	
}
