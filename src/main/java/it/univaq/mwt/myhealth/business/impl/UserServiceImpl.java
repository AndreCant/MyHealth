package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.RoleDao;
import it.univaq.mwt.myhealth.dao.UserDao;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<User> findAllUsers() throws BusinessException {
		return userDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		return userDao.findByUsername(username);
	}

	@Override
	public void saveUser(User user) throws BusinessException {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) throws BusinessException {
		userDao.update(user);
	}

	@Override
	public void deleteUser(Long uid) throws BusinessException {
		userDao.delete(uid);
	}

	@Override
	public void saveRole(Role role) throws BusinessException {
		try {
			roleDao.save(role);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Role findRoleByName(String name) throws BusinessException {
		try {
			return roleDao.findByName(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException("getRoleByName", e);
		}
	}
}
