package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.User;

public interface UserDao {
	
	public List<User> findAll() throws DaoException;
	
	public User findById(Long uid) throws DaoException;
	
	public User findByUsername(String username) throws DaoException;
   
	public void save(User user) throws DaoException;
	
	public void saveAll(List<User> users) throws DaoException;
 
	public void update(User user) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
}