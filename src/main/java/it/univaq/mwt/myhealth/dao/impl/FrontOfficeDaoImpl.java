package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.FrontOfficeDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;

@Repository
public class FrontOfficeDaoImpl implements FrontOfficeDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;


	@Override
	public List<FrontOffice> findAll(){
		return entityManager.createQuery("FROM Exam").getResultList();
	}

	@Override
	public FrontOffice findById(long id) {		
		return entityManager.find(FrontOffice.class, id);
	}

	@Override
	public void save(FrontOffice frontOffice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(FrontOffice frontOffice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) {
		// TODO Auto-generated method stub
		
	}
}
