package it.univaq.mwt.myhealth.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.domain.Paycheck;

@Repository
public interface PaycheckRepository extends CrudRepository<Paycheck, String>{

	List<Paycheck> findByRegister(int register);

}
