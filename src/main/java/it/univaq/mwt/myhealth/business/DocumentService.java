package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Paycheck;

public interface DocumentService {
	
	void saveInvoice(Invoice invoice) throws BusinessException;
	
	void saveInvoices(List<Invoice> invoices) throws BusinessException;
	
	List<Invoice> findAllInvoices() throws BusinessException;
	
	List<Paycheck> findAllPaychecks() throws BusinessException;
	
	void savePaychecks(List<Paycheck> paychecks) throws BusinessException;
	
	List<Paycheck> findPaychecksByRegister(int register) throws BusinessException;
}
