package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Invoice;

public interface DocumentService {
	
	void saveInvoice(Invoice invoice) throws BusinessException;
	
	void saveInvoices(List<Invoice> invoices) throws BusinessException;
	
	List<Invoice> findAllInvoices() throws BusinessException;
}
