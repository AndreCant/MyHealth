package it.univaq.mwt.myhealth.dao;

import java.util.List;

import it.univaq.mwt.myhealth.domain.Reservation;

public interface ReservationDao {
	
	public List<Reservation> findAll();
	
	public Reservation findById(Long uid);
   
	public void save(Reservation reservation);
 
	public void update(Reservation reservation);
  
	public void delete(Long uid);
}
