package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.FrontOffice;

public interface FrontOfficeDao{
	
	public List<FrontOffice> findAll();
	
	public FrontOffice findById(Long uid);
   
	public void save(FrontOffice frontOffice);
 
	public void update(FrontOffice frontOffice);
  
	public void delete(Long uid);
}
