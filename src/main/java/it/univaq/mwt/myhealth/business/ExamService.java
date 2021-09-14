package it.univaq.mwt.myhealth.business;

import java.util.List;
import java.util.Set;

import it.univaq.mwt.myhealth.domain.Exam;
import it.univaq.mwt.myhealth.domain.Image;
import it.univaq.mwt.myhealth.domain.Review;

public interface ExamService {
	
	List<Exam> findAllExams() throws BusinessException;
	
	List<Exam> findExamsByType(String type) throws BusinessException;
	
	List<Exam> findExamsBySpecialization(String specialization) throws BusinessException;
	
	Exam findById(Long uid) throws BusinessException;
	
	Exam findByName (String name) throws BusinessException;
	   
	void save(Exam exam) throws BusinessException;
 
	void update(Exam exam) throws BusinessException;
  
	void delete(Long uid) throws BusinessException;
	
	void saveExams(List<Exam> exams) throws BusinessException;
	
	List<Review> findExamReview(Set<Long> examIds) throws BusinessException;
	
	void saveImages(List<Image> images) throws BusinessException;
}
