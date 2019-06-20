package com.backend.psoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.psoft.model.Profile;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
public interface ProfileDAO extends JpaRepository<Profile, Long> {
	
	Profile save(Profile profile);
	
	@Query("Select p from Profile p where p.id=:pid")
	Profile findById(@Param("pid") long id);
	
}
