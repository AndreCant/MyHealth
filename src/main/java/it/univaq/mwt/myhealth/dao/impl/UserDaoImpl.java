package it.univaq.mwt.myhealth.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.UserDao;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		return (List<User>) entityManager.createQuery("FROM User").getResultList();
	}
	
	@Override
	public User findById(Long uid) {
		return (User) entityManager.find(User.class, uid);
	}

	@Override
	public void save(User user) {
		 entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));
	}

	@Override
	public User findByUsername(String username) {
		List<User> users = entityManager.createQuery("FROM User where username = :username", User.class).setParameter("username", username).getResultList();
		if(!users.isEmpty()) return users.get(0);
		return null;
	}

	@Override
	public void saveAll(List<User> users) {
		for (User user : users) {
			entityManager.persist(user);
		}
	}
	
}
