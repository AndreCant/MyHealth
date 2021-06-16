package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

public interface UserService {
	
	List<User> findAllUsers() throws BusinessException;
	   
	void saveUser(User user) throws BusinessException;
 
	void updateUser(User user) throws BusinessException;
  
	void deleteUser(Long uid) throws BusinessException;
	
	User findUserByUsername(String username) throws BusinessException;
	
	void saveRole(Role role) throws BusinessException;
	
	Role findRoleByName(String name) throws BusinessException;
}
