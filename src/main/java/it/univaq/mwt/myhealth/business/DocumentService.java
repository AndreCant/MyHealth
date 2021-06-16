package it.univaq.mwt.myhealth.business;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Invoice;

public interface DocumentService {
	
	void saveInvoice(Invoice invoice) throws BusinessException;
}
