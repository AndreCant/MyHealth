package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ImageDao;
import it.univaq.mwt.myhealth.domain.Image;

@Repository
public class ImageDaoImpl implements ImageDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Image> findAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image findById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Image image) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Image image) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAll(List<Image> images) throws DaoException {
		for (Image image : images) {
			entityManager.persist(image);
		}
	}

}
