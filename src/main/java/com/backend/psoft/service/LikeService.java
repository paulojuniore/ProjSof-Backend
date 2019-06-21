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
	
	/*
	 * Precisa receber um Json que contenha um like, o conteudo desse Json é
	 * o email do usuario, o id da disciplina e o tipo de avaliação que ele deu,
	 * True para like e False para deslike.
	 */
	public Like create(Like like) throws ServletException {
		String emailUser = like.getEmailUser();
		long subjectId = like.getIdSubject();
		Boolean option = like.getLike_type();
		User user = userDAO.findByEmail(emailUser);
		Subject subject = subjectDAO.findById(subjectId);
		if(user != null && subject != null) {
			Like newLike = new Like();
			newLike.setEmailUser(emailUser);
			newLike.setIdSubject(subjectId);
			String nameUser = (user.getFirstName() + " " + user.getLastName());
			newLike.setNameUser(nameUser);
			if(option) {
				newLike.setLike_type(true);
			} else {
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
