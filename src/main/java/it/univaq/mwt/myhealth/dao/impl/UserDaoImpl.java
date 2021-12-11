package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.UserDao;
import it.univaq.mwt.myhealth.domain.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return (List<User>) entityManager.createQuery("FROM User").getResultList();
	}
	
	@Override
	public User findById(Long uid) {
		return (User) entityManager.find(User.class, uid);
	}
	
	@Override
	public User findByName (String name) {
		List<User> users = entityManager.createQuery("FROM User where name = :name", User.class).setParameter("name", name).getResultList();
		if(!users.isEmpty()) return users.get(0);
		return null;
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

	@Override
	public List<User> findByRole(long role) throws DaoException {
		List<User> users = entityManager.createQuery("FROM User where role_id = :role", User.class).setParameter("role", role).getResultList();
		return  users;
	}

	@Override
	public User findUserById(long id) throws DaoException {
		return (User) entityManager.find(User.class,id);
	}

	@Override
	public User findRandomDoctor(long id) throws DaoException {
		User user = entityManager.createQuery("FROM User where id = :id  and role_id = 2", User.class).setParameter("id", id).getSingleResult();
		return  user;
	}

	@Override
	public User findByEmail(String email) throws DaoException {
		List<User> users = entityManager.createQuery("FROM User where email = :email", User.class).setParameter("email", email).getResultList();
		if(!users.isEmpty()) return users.get(0);
		return null;
	}

	@Override
	public User findByFiscalCode(String fiscalCode) throws DaoException {
		List<User> users = entityManager.createQuery("FROM User where fiscalCode = :fiscalCode", User.class).setParameter("fiscalCode", fiscalCode).getResultList();
		if(!users.isEmpty()) return users.get(0);
		return null;
	}
	
}
