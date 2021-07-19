package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.RoleDao;
import it.univaq.mwt.myhealth.domain.Role;

@Repository
public class RoleDaoImpl implements RoleDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public void save(Role role) throws DaoException {
		 entityManager.persist(role);
	}

	@Override
	public Role findByName(String name) throws DaoException {
		return (Role) entityManager.createQuery("FROM Role where name = :name", Role.class)
        .setParameter("name", name)
        .getResultList().get(0);
	}

	@Override
	public void saveAll(List<Role> roles) throws DaoException {
		for (Role role : roles) {
			entityManager.persist(role);
		}
	}
	
}
