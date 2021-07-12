package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.dao.ReservationDao;
import it.univaq.mwt.myhealth.domain.Reservation;
@Repository
public class ReservationDaoImpl implements ReservationDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Reservation> findAll() {
	   List<Reservation> listReservation = (List<Reservation>) entityManager.createQuery("SELECT * FROM Reservation");
		return listReservation;
	}

	@Override
	public Reservation findById(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long uid) {
		// TODO Auto-generated method stub
		
	}

}
