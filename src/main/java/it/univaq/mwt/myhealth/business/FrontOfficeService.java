package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.FrontOffice;

public interface FrontOfficeService {

	List<FrontOffice> findAllFrontOffice() throws BusinessException, DaoException;
	
	List<FrontOffice> findFrontOfficeByType(String type) throws BusinessException;
	
	List<FrontOffice> findFrontOfficeBySpecialization(String specialization) throws BusinessException;
	
	FrontOffice findById(long id) throws BusinessException;
	
	FrontOffice findByName (String name) throws BusinessException;
	
	void saveFrontOffices(List<FrontOffice> frontOffices) throws BusinessException;
}
