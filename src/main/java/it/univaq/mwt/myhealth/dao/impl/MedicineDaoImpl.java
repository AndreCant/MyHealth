package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.MedicineDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class MedicineDaoImpl implements MedicineDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public List<Medicine> findAll() throws DaoException {
		return (List<Medicine>) entityManager.createQuery("FROM Medicine").getResultList();
	}

	@Override
	public Medicine findById(Long uid) throws DaoException {
		return (Medicine) entityManager.find(Medicine.class, uid);
	}

	@Override
	public void save(Medicine medicine) throws DaoException {
		 entityManager.persist(medicine);
	}

	@Override
	public void update(Medicine medicine) throws DaoException {
		entityManager.merge(medicine);
	}

	@Override
	public void delete(Long uid) throws DaoException {
		entityManager.remove(this.findById(uid));
	}

	@Override
	public void saveAll(List<Medicine> medicines) throws DaoException {
		for (Medicine medicine : medicines) {
			entityManager.persist(medicine);
		}
	}

}
