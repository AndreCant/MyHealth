 package it.univaq.mwt.myhealth.dao;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Role;

public interface RoleDao {
	
	void save(Role role) throws DaoException;
	
	Role findByName(String name) throws DaoException;
}
