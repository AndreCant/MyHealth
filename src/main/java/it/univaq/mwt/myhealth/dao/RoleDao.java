 package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Role;

public interface RoleDao {
	
	void save(Role role) throws DaoException;
	
	Role findByName(String name) throws DaoException;
	
	void saveAll(List<Role> roles) throws DaoException;
}
