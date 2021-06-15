package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Invoice;

public interface InvoiceDao {
	
	public List<Invoice> findAll();
	
	public Invoice findById(Long uid);
   
	public void save(Invoice invoice);
 
	public void update(Invoice invoice);
  
	public void delete(Long uid);
}
