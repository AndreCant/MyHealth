package it.univaq.mwt.myhealth.business;

import java.util.List;

import it.univaq.mwt.myhealth.business.exceptions.BusinessException;
import it.univaq.mwt.myhealth.domain.Exam;

public interface ExamService {
	
	List<Exam> findAllExams() throws BusinessException;
	
	List<Exam> findExamsByType(String type) throws BusinessException;
	
	List<Exam> findExamsBySpecialization(String specialization) throws BusinessException;
	
	Exam findById(Long uid) throws BusinessException;
	
	Exam findByName (String name) throws BusinessException;
	   
	void save(Exam exam) throws BusinessException;
 
	void update(Exam exam) throws BusinessException;
  
	void delete(Long uid) throws BusinessException;
}
