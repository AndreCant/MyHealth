package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.User;

public interface UserService {
	
	public List<User> findAllUsers();
	   
	public void save(User user);
 
	public void update(User user);
  
	public void delete(Long uid);
}
