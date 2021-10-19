package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.DocumentService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.InvoiceDao;
import it.univaq.mwt.myhealth.dao.PaycheckDao;
import it.univaq.mwt.myhealth.domain.Invoice;
import it.univaq.mwt.myhealth.domain.Paycheck;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private PaycheckDao paycheckDao;

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

	@Override
	public List<Paycheck> findAllPaychecks() throws BusinessException {
		try {
			return paycheckDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void savePaychecks(List<Paycheck> paychecks) throws BusinessException {
		try {
			paycheckDao.saveAll(paychecks);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Paycheck> findPaychecksByRegister(int register) throws BusinessException {
		try {
			return paycheckDao.findByRegister(register);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
