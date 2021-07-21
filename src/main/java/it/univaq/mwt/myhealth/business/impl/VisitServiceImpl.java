package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.VisitService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Visit;

@Service
@Transactional
public class VisitServiceImpl implements VisitService{
	
	@Autowired
	private VisitDao visitDao;
	

	@Override
	public List<Visit> findAllUsers() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveVisit(Visit visit) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Visit findById(Long id) throws BusinessException {
		try {
			return visitDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateVisit(Visit visit) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVisit(Long uid) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
