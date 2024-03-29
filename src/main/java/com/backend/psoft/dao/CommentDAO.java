package com.backend.psoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.psoft.model.Comment;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Repository
public interface CommentDAO extends JpaRepository<Comment, Long>{
	
	@SuppressWarnings("unchecked")
	Comment save(Comment comment);
	
	@Query(value="Select c from Comment c where c.id=:cid")
	Comment findById(@Param("cid") long id);

}
