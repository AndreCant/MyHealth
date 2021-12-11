package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Image;

public interface ImageDao {
	
	public void saveAll(List<Image> images) throws DaoException;
}
