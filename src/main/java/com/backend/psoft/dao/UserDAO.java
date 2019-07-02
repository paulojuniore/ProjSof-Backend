package com.backend.psoft.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.psoft.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
	
	@SuppressWarnings("unchecked")
	User save(User user);
	
	List<User> findAll();
	
	@Query(value="Select u from User u where u.email=:pemail")
	User findByEmail(@Param("pemail") String email);
	
	@Transactional
	@Modifying
	@Query(value="Delete from User u where u.email=:pemail")
	void deleteByEmail(@Param("pemail") String email);

}
