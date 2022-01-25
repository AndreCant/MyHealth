package it.univaq.mwt.myhealth.dao;

import java.util.List;
import java.util.Set;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.domain.Image;

public interface ImageDao {
	
	public void saveAll(List<Image> images) throws DaoException;
	
	public List<Image> findImagesByUserIds(Set<Long> ids) throws BusinessException;
}
