package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.FrontOfficeDao;
import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.FrontOffice;

@Repository
public class FrontOfficeDaoImpl implements FrontOfficeDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<FrontOffice> findAll(){
		return entityManager.createQuery("FROM FrontOffice").getResultList();
	}

	@Override
	public FrontOffice findById(Long id) {		
		return entityManager.find(FrontOffice.class, id);
	}

	@Override
	public void save(FrontOffice frontOffice) {
		entityManager.persist(frontOffice);
	}

	@Override
	public void update(FrontOffice frontOffice) {
		entityManager.merge(frontOffice);
	}

	@Override
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));
	}

	@Override
	public void saveAll(List<FrontOffice> frontOffices) throws DaoException {
		for (FrontOffice frontOffice : frontOffices) {
			entityManager.persist(frontOffice);
		}
	}
}
