package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.AnnualBudget;
import it.univaq.mwt.myhealth.domain.Expense;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.Payment;

public interface AdministrationService {
	
	void saveExpenses(List<Expense> expenses) throws BusinessException;
	
	void saveAnnualBudgets(List<AnnualBudget> annualBudgets) throws BusinessException;
	
	List<Payment> findAllPayments() throws BusinessException;
	
	List<Medicine> findAllMedicines() throws BusinessException;
	
	void saveMedicine(Medicine medicine) throws BusinessException;
	
	void updateMedicine(Medicine medicine) throws BusinessException;
	
	void deleteMedicine(Long id) throws BusinessException;
	
	Medicine findMedicineById(Long id) throws BusinessException;
	
	void saveFrontOffices(List<FrontOffice> frontOffices) throws BusinessException;
	
	FrontOffice findFrontOfficeById(Long id) throws BusinessException;
	
	List<FrontOffice> findAllFrontOffices() throws BusinessException;
	
	void updateFrontOffice(FrontOffice frontOffice) throws BusinessException;
	
	void deleteFrontOffice(Long id) throws BusinessException;
	
	List<Expense> findAllExpenses() throws BusinessException;
	
	List<AnnualBudget> findAllAnnualBudgets() throws BusinessException;
	
	AnnualBudget findAnnualBudgetById() throws BusinessException;
	
}
