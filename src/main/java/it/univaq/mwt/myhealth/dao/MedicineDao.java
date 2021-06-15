package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Medicine;

public interface MedicineDao {
	
	public List<Medicine> findAll();
	
	public Medicine findById(Long uid);
   
	public void save(Medicine medicine);
 
	public void update(Medicine medicine);
  
	public void delete(Long uid);
}
