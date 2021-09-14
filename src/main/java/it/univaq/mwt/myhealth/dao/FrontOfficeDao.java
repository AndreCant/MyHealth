package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.FrontOffice;

public interface FrontOfficeDao{
	
	public List<FrontOffice> findAll() throws DaoException;
	
	public FrontOffice findById(long id)  throws DaoException;
   
	public void save(FrontOffice frontOffice)  throws DaoException;
 
	public void update(FrontOffice frontOffice)  throws DaoException;
  
	public void delete(Long uid)  throws DaoException;
	
	public void saveAll(List<FrontOffice> frontOffices) throws DaoException;
}
