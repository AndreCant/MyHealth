package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

public interface UserService {
	
	List<User> findAllUsers() throws BusinessException;
	   
	void saveUser(User user) throws BusinessException;
	
	void saveUsers(List<User> users) throws BusinessException;
 
	void updateUser(User user) throws BusinessException;
  
	void deleteUser(Long uid) throws BusinessException;
	
	User findUserByUsername(String username) throws BusinessException;
	
	List<User> findUserByRole(long role) throws BusinessException;
	
	void saveRole(Role role) throws BusinessException;
	
	void saveRoles(List<Role> roles) throws BusinessException;
	
	Role findRoleByName(String name) throws BusinessException;

	User findByName(String name) throws BusinessException;
	
	User findUserById (long id) throws BusinessException;
}
