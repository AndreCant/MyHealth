package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Invoice;

public interface InvoiceDao {
	
	public List<Invoice> findAll() throws DaoException;
	
	public Invoice findById(Long uid) throws DaoException;
   
	public void save(Invoice invoice) throws DaoException;
 
	public void update(Invoice invoice) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Invoice> invoices) throws DaoException;
}
