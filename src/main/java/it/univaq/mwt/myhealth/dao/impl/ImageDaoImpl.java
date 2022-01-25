package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ImageDao;
import it.univaq.mwt.myhealth.domain.Image;

@Repository
public class ImageDaoImpl implements ImageDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public void saveAll(List<Image> images) throws DaoException {
		for (Image image : images) {
			entityManager.persist(image);
		}
	}

	@Override
	public List<Image> findImagesByUserIds(Set<Long> ids) throws BusinessException {
		return (List<Image>) entityManager
				.createQuery("FROM Image img WHERE img.user.id IN (:ids)")
				.setParameter("ids", ids).getResultList();
	}

}
