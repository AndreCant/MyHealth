package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Reservation;
import it.univaq.mwt.myhealth.domain.Review;
import it.univaq.mwt.myhealth.domain.Visit;

public interface ReservationService {
	
	List<Reservation> findAllReservations() throws BusinessException;
	
	List<Reservation> findReservationsByPatient(Long patientId) throws BusinessException;
	
	Reservation findReservationById(Long uid) throws BusinessException;
	   
	void save(Reservation reservation) throws BusinessException;
 
	void update(Reservation reservation) throws BusinessException;
  
	void delete(Long uid) throws BusinessException;
	
	void saveReservations(List<Reservation> reservations) throws BusinessException;
	
	void saveVisits(List<Visit> visits) throws BusinessException;
	
	void saveReviews(List<Review> reviews) throws BusinessException;
}
