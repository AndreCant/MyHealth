package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.PaycheckDao;
import it.univaq.mwt.myhealth.dao.repository.PaycheckRepository;
import it.univaq.mwt.myhealth.domain.Paycheck;

@Repository
public class PaycheckDaoImpl implements PaycheckDao{
	
	@Autowired
	private PaycheckRepository paycheckRepository;

	@Override
	public List<Paycheck> findAll() throws DaoException {
		return (List<Paycheck>) paycheckRepository.findAll();
	}

	@Override
	public void save(Paycheck paycheck) throws DaoException {
		paycheckRepository.save(paycheck);
	}

	@Override
	public void saveAll(List<Paycheck> paychecks) throws DaoException {
		for (Paycheck paycheck : paychecks) {
			paycheckRepository.save(paycheck);
		}
	}

	@Override
	public List<Paycheck> findByRegister(int register) throws DaoException {
		return paycheckRepository.findByRegister(register);
	}

}
