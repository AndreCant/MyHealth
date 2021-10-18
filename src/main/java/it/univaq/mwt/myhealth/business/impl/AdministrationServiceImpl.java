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
import it.univaq.mwt.myhealth.dao.FrontOfficeDao;
import it.univaq.mwt.myhealth.dao.MedicineDao;
import it.univaq.mwt.myhealth.dao.PaymentDao;
import it.univaq.mwt.myhealth.domain.AnnualBudget;
import it.univaq.mwt.myhealth.domain.Expense;
import it.univaq.mwt.myhealth.domain.FrontOffice;
import it.univaq.mwt.myhealth.domain.Medicine;
import it.univaq.mwt.myhealth.domain.Payment;

@Service
@Transactional
public class AdministrationServiceImpl implements AdministrationService{
	
	@Autowired ExpenseDao expenseDao;
	@Autowired AnnualBudgetDao annualBudgetDao;
	@Autowired PaymentDao paymentDao;
	@Autowired MedicineDao medicineDao;
	@Autowired FrontOfficeDao frontOfficeDao;

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

	@Override
	public List<Medicine> findAllMedicines() throws BusinessException {
		try {
			return medicineDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveMedicine(Medicine medicine) throws BusinessException {
		try {
			medicineDao.save(medicine);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateMedicine(Medicine medicine) throws BusinessException {
		try {
			medicineDao.update(medicine);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void deleteMedicine(Long id) throws BusinessException {
		try {
			medicineDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Medicine findMedicineById(Long id) throws BusinessException {
		try {
			return medicineDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveFrontOffices(List<FrontOffice> frontOffices) throws BusinessException {
		try {
			frontOfficeDao.saveAll(frontOffices);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public FrontOffice findFrontOfficeById(Long id) throws BusinessException {
		try {
			return frontOfficeDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<FrontOffice> findAllFrontOffices() throws BusinessException {
		try {
			return frontOfficeDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void updateFrontOffice(FrontOffice frontOffice) throws BusinessException {
		try {
			frontOfficeDao.update(frontOffice);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void deleteFrontOffice(Long id) throws BusinessException {
		try {
			frontOfficeDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Expense> findAllExpenses() throws BusinessException {
		try {
			return expenseDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<AnnualBudget> findAllAnnualBudgets() throws BusinessException {
		try {
			return annualBudgetDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
