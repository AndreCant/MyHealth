package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Image;

public interface ImageDao {
	
	public List<Image> findAll() throws DaoException;
	
	public Image findById(long id)  throws DaoException;
   
	public void save(Image image)  throws DaoException;
 
	public void update(Image image)  throws DaoException;
  
	public void delete(Long uid)  throws DaoException;
	
	public void saveAll(List<Image> images) throws DaoException;
}
