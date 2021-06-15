package it.univaq.mwt.myhealth.business;

import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.domain.Invoice;

public interface DocumentService {
	
	public void saveInvoice(Invoice invoice);
}
