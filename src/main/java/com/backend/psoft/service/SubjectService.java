package com.backend.psoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.model.Subject;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class SubjectService {
	
	private final SubjectDAO subjectDAO;
	
	public SubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	public Subject findById(long id) {
		return this.subjectDAO.findById(id);
	}
	
	public Subject findByName(String name) {
		return this.subjectDAO.findBySubjectName(name);
	}
	
	public Subject create(Subject subject) {
		return this.subjectDAO.save(subject);
	}
	
	public List<Subject> list() {
		return this.subjectDAO.findAll();
	}
	
	public void delete(long id) {
		this.subjectDAO.deleteById(id);
	}

}
