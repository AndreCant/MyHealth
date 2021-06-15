package it.univaq.mwt.myhealth.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.univaq.mwt.myhealth.domain.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String>{}
