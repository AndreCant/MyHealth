package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.InvoiceDao;
import it.univaq.mwt.myhealth.dao.repository.InvoiceRepository;
import it.univaq.mwt.myhealth.domain.Invoice;

@Repository
public class InvoiceDaoImpl implements InvoiceDao{
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> findAll() throws DaoException {
		return (List<Invoice>) invoiceRepository.findAll();
	}

	@Override
	public Invoice findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Invoice invoice) throws DaoException {
		invoiceRepository.save(invoice);
	}

	@Override
	public void update(Invoice invoice) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<Invoice> invoices) throws DaoException {
		for (Invoice invoice : invoices) {
			invoiceRepository.save(invoice);
		}
	}

}
