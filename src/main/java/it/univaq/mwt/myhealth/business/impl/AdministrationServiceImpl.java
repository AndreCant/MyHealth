package it.univaq.mwt.myhealth.business.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.myhealth.business.AdministrationService;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.dao.AnnualBudgetDao;
import it.univaq.mwt.myhealth.dao.DaoException;
import it.univaq.mwt.myhealth.dao.ExpenseDao;
import it.univaq.mwt.myhealth.dao.PaymentDao;
import it.univaq.mwt.myhealth.domain.AnnualBudget;
import it.univaq.mwt.myhealth.domain.Expense;
import it.univaq.mwt.myhealth.domain.Payment;

@Service
@Transactional
public class AdministrationServiceImpl implements AdministrationService{
	
	@Autowired
	ExpenseDao expenseDao;
	
	@Autowired
	AnnualBudgetDao annualBudgetDao;
	
	@Autowired
	PaymentDao paymentDao;

	@Override
	public void saveExpenses(List<Expense> expenses) throws BusinessException {
		try {
			expenseDao.saveAll(expenses);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public void saveAnnualBudgets(List<AnnualBudget> annualBudgets) throws BusinessException {
		try {
			annualBudgetDao.saveAll(annualBudgets);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public List<Payment> findAllPayments() throws BusinessException {
		try {
			return paymentDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
