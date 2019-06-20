package com.backend.psoft.service;

import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.psoft.dao.LikeDAO;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.model.Like;
import com.backend.psoft.model.Subject;
import com.backend.psoft.model.User;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class LikeService {
	
	private final LikeDAO likeDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SubjectDAO subjectDAO;
	
	public LikeService(LikeDAO likeDAO) {
		this.likeDAO = likeDAO;
	}
	
	public Like create(long id, String email, String option) throws ServletException {
		User user = userDAO.findByEmail(email);
		Subject subject = subjectDAO.findById(id);
		if(user != null && subject != null) {
			Like newLike = new Like();
			newLike.setUser(user);
			newLike.setSubject(subject);
			if(option.equalsIgnoreCase("like")) {
				newLike.setLike_type(true);
			} else if(option.equalsIgnoreCase("unlike")) {
				newLike.setLike_type(false);
			}
			return likeDAO.save(newLike);
		} 
		throw new ServletException("Usuário/disciplinas inexistente(s).");
	}
	
	public Like findById(long id) {
		return likeDAO.findById(id);
	}

}
