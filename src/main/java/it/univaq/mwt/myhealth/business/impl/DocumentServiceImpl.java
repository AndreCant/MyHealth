package it.univaq.mwt.myhealth.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.dao.InvoiceDao;
import it.univaq.mwt.myhealth.domain.Invoice;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	public void saveInvoice(Invoice invoice) {
		invoiceDao.save(invoice);
	}

}
