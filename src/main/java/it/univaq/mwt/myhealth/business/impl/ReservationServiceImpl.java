package it.univaq.mwt.myhealth.business.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.univaq.mwt.myhealth.business.ReservationService;
import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.PaymentDao;
import it.univaq.mwt.myhealth.dao.ReservationDao;
import it.univaq.mwt.myhealth.dao.ReviewDao;
import it.univaq.mwt.myhealth.dao.VisitDao;
import it.univaq.mwt.myhealth.domain.Payment;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.Visit;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private PaymentDao paymentDao;

	@Override
	public List<Reservation> findAllReservations() throws BusinessException {
		try {
			return reservationDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Reservation> findReservationsByPatient(Long patientId) throws BusinessException {
		try {
			return reservationDao.findReservationByPatient(patientId);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Reservation findReservationById(Long uid) throws BusinessException {
		try {
			return reservationDao.findById(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void save(Reservation reservation) throws BusinessException {
		try {
			reservationDao.save(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void update(Reservation reservation) throws BusinessException {
		try {
			reservationDao.update(reservation);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		
	}

	@Override
	public void delete(Long uid) throws BusinessException {
		try {
			reservationDao.delete(uid);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveReservations(List<Reservation> reservations) throws BusinessException {
		try {
			reservationDao.saveAll(reservations);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void saveReviews(List<Review> reviews) throws BusinessException {
		try {
			reviewDao.saveAll(reviews);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public void savePayments(List<Payment> payments) throws BusinessException {
		try {
			paymentDao.saveAll(payments);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
