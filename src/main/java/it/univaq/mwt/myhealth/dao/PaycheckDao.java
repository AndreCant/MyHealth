package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Paycheck;

public interface PaycheckDao {
	
	public List<Paycheck> findAll() throws DaoException;
   
	public void save(Paycheck paycheck) throws DaoException;
	
	public void saveAll(List<Paycheck> paychecks) throws DaoException;

	public List<Paycheck> findByRegister(int register) throws DaoException;
}
