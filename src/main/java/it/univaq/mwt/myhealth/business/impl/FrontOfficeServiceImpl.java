package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.FrontOfficeService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.FrontOfficeDao;
import it.univaq.mwt.myhealth.domain.FrontOffice;

@Service
@Transactional
public class FrontOfficeServiceImpl implements FrontOfficeService{
	
	@Autowired
	private FrontOfficeDao frontOfficeDao;

	@Override
	public List<FrontOffice> findAllFrontOffice() throws BusinessException {
		try {
			return frontOfficeDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FrontOffice> findFrontOfficeByType(String type) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FrontOffice> findFrontOfficeBySpecialization(String specialization) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontOffice findById(long id) throws BusinessException {
		try {
			return frontOfficeDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public FrontOffice findByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveFrontOffices(List<FrontOffice> frontOffices) throws BusinessException {
		try {
			frontOfficeDao.saveAll(frontOffices);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

}
