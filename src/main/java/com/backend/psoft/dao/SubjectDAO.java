package com.backend.psoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.psoft.model.Subject;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Repository
public interface SubjectDAO extends JpaRepository<Subject, Long> {
	
	Subject save(Subject subject);
	
	List<Subject> findAll();
	
	@Query(value="Select u from User u where u.id=:pid")
	Subject findById(@Param("pid") long id);
	
}
