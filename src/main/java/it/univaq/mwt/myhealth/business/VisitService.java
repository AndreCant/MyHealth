package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Visit;

public interface VisitService {
	
	List<Visit> findAllUsers() throws BusinessException;
	   
	void saveVisit(Visit visit) throws BusinessException;
	 
	void updateVisit(Visit visit) throws BusinessException;
  
	void deleteVisit(Long uid) throws BusinessException;

	Visit findById(Long id) throws BusinessException;
	

}
