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
	
	@SuppressWarnings("unchecked")
	Subject save(Subject subject);
	
	List<Subject> findAll();
	
	@Query(value="Select u from Subject u where u.id=:pid")
	Subject findById(@Param("pid") long id);

	@Query(value="Select n from Subject n where n.subjectName=:pname")
	Subject findBySubjectName(@Param("pname") String name);

	/**
	 * @author Hércules Rodrigues
	 * @param subString
	 * @return Lista de disciplinas iniciadas com a subString 
	 */
	@Query(value="SELECT s FROM Subject s WHERE s.subjectName LIKE %:psubString%")
	List<Subject> getSubjectBySubString(@Param("psubString") String subString);
}
