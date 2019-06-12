package com.backend.psoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.psoft.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
	
	User save(User user);
	
	List<User> findAll();
	
	@Query(value="Select u from User u where u.email=:pemail")
	User findByEmail(@Param("pemail") String email);

}
