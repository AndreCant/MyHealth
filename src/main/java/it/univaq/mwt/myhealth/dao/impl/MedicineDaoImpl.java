package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.MedicineDao;
import it.univaq.mwt.myhealth.domain.Medicine;

@Repository
public class MedicineDaoImpl implements MedicineDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public List<Medicine> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicine findById(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Medicine medicine) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Medicine medicine) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<Medicine> medicines) throws DaoException {
		for (Medicine medicine : medicines) {
			entityManager.persist(medicine);
		}
	}

}
