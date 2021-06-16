package it.univaq.mwt.myhealth.dao.repository;

import org.springframework.data.repository.CrudRepository;

import it.univaq.mwt.myhealth.domain.Paycheck;

public interface PaycheckRepository extends CrudRepository<Paycheck, String>{}
