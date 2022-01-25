package it.univaq.mwt.myhealth.business.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ImageDao;
import it.univaq.mwt.myhealth.dao.RoleDao;
import it.univaq.mwt.myhealth.dao.UserDao;
import it.univaq.mwt.myhealth.domain.Image;
import it.univaq.mwt.myhealth.domain.Role;
import it.univaq.mwt.myhealth.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private ImageDao imageDao;
	
	@Override
	public List<User> findAllUsers() throws BusinessException {
		try {
			return userDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public User findByName(String name) throws BusinessException {
		try {
			return userDao.findByName(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
	
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		try {
			return userDao.findByUsername(username);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveUser(User user) throws BusinessException {
		try {
			userDao.save(user);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) throws BusinessException {
		try {
			userDao.update(user);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void deleteUser(Long uid) throws BusinessException {
		try {
			userDao.delete(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveRole(Role role) throws BusinessException {
		try {
			roleDao.save(role);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Role findRoleByName(String name) throws BusinessException {
		try {
			return roleDao.findByName(name);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException("getRoleByName", e);
		}
	}

	@Override
	public void saveRoles(List<Role> roles) throws BusinessException {
		try {
			roleDao.saveAll(roles);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveUsers(List<User> users) throws BusinessException {
		try {
			userDao.saveAll(users);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<User> findUserByRole(long role) throws BusinessException {
		try {
			return userDao.findByRole(role);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public User findUserById(long id) throws BusinessException {
		try {
			return userDao.findUserById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public User findRandomDoctor(long id) throws BusinessException {
		try {
			return userDao.findRandomDoctor(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public boolean existsByEmail(String email) throws BusinessException {
		User user;
		try {
			user = userDao.findByEmail(email);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return user != null;
	}

	@Override
	public boolean existsByFiscalCode(String fiscalCode) throws BusinessException {
		User user;
		try {
			user = userDao.findByFiscalCode(fiscalCode);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return user != null;
	}

	@Override
	public boolean existsByUsername(String username) throws BusinessException {
		User user;
		try {
			user = userDao.findByUsername(username);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return user != null;
	}

	@Override
	public List<Image> findImagesByUserIds(Set<Long> ids) throws BusinessException {
		return imageDao.findImagesByUserIds(ids);
	}

}
