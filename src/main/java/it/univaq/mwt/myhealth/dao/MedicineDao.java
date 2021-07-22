package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Medicine;

public interface MedicineDao {
	
	public List<Medicine> findAll() throws DaoException;
	
	public Medicine findById(Long uid) throws DaoException;
   
	public void save(Medicine medicine) throws DaoException;
 
	public void update(Medicine medicine) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Medicine> medicines) throws DaoException;
}
