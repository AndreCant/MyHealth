package it.univaq.mwt.myhealth.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.business.exceptions.DaoException;
import it.univaq.mwt.myhealth.dao.ReservationDao;
import it.univaq.mwt.myhealth.domain.Reservation;
@Repository
public class ReservationDaoImpl implements ReservationDao{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public List<Reservation> findAll() {
	   return (List<Reservation>) entityManager.createQuery("FROM Reservation").getResultList();
	}

	@Override
	public Reservation findById(Long uid) {
		return (Reservation) entityManager.find(Reservation.class, uid);
	}

	@Override
	public void save(Reservation reservation) {
		entityManager.persist(reservation);
	}

	@Override
	public void update(Reservation reservation) {
		entityManager.merge(reservation);
	}

	@Override
	public void delete(Long uid) {
		entityManager.remove(this.findById(uid));
	}

	@Override
	public List<Reservation> findReservationByPatient(Long patientId) throws DaoException {
		return (List<Reservation>) entityManager.createQuery("FROM Reservation WHERE patient_id = :patientId", Reservation.class)
		        .setParameter("patientId", patientId)
		        .getResultList();
	}

	@Override
	public void saveAll(List<Reservation> reservations) throws DaoException {
		for (Reservation reservation : reservations) {
			entityManager.persist(reservation);
		}
	}

}
