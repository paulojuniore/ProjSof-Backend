package com.backend.psoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.psoft.model.Like;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Repository
public interface LikeDAO extends JpaRepository<Like, Long> {
	
	@SuppressWarnings("unchecked")
	Like save(Like like);
	
	@Query(value="Select l from Like l where l.id=:lid")
	Like findById(@Param("lid") long id);
	
}
