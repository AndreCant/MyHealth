package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.domain.Reservation;

public interface ReservationDao {
	
	public List<Reservation> findAll() throws DaoException;
	
	public List<Reservation> findReservationByPatient(Long patientId) throws DaoException;
	
	public Reservation findById(Long uid) throws DaoException;
   
	public void save(Reservation reservation) throws DaoException;
 
	public void update(Reservation reservation) throws DaoException;
  
	public void delete(Long uid) throws DaoException;
	
	public void saveAll(List<Reservation> reservations) throws DaoException;
}
