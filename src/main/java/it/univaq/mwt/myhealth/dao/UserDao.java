package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.User;

public interface UserDao {
	
	public List<User> findAllUsers();
   
	public void save(User user);
 
	public void update(User user);
  
	public void delete(Long uid);
}