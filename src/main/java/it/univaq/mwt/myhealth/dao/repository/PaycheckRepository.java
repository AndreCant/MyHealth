package it.univaq.mwt.myhealth.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.domain.Paycheck;

@Repository
public interface PaycheckRepository extends CrudRepository<Paycheck, String>{}
