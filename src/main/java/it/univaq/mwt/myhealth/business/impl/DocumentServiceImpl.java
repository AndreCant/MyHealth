package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.InvoiceDao;
import it.univaq.mwt.myhealth.domain.Invoice;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	public void saveInvoice(Invoice invoice) throws BusinessException{
		try {
			invoiceDao.save(invoice);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveInvoices(List<Invoice> invoices) throws BusinessException {
		try {
			invoiceDao.saveAll(invoices);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Invoice> findAllInvoices() throws BusinessException {
		try {
			return invoiceDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
