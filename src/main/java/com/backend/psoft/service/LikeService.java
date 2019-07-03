package com.backend.psoft.service;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.psoft.dao.LikeDAO;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.exception.subjects.NotExistentDisciplineException;
import com.backend.psoft.exception.users.NotExistentUserException;
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
	 * 1 para like e -1 para deslike.
	 * 
	 * Seu nome é pego automaticamente e armazenado no like.
	 * 
	 * O like já está sendo salvo na disciplina.
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public Integer[] create(Like like) throws NotExistentUserException, NotExistentDisciplineException, ServletException {
		Integer[] retorno = new Integer[2];
		String emailUser = like.getEmailUser();
		long subjectId = like.getIdSubject();
		Integer option = like.getLike_type();
		User user = userDAO.findByEmail(emailUser);
		Subject subject = subjectDAO.findById(subjectId);
		if(user != null && subject != null) {
			Like newLike = new Like();
			newLike.setEmailUser(emailUser);
			newLike.setIdSubject(subjectId);
			String nameUser = (user.getFirstName() + " " + user.getLastName());
			newLike.setNameUser(nameUser);

			// Likes podem vim com -1 ou 1
			if(option == 1) {
				newLike.setLike_type(1);
			} else if(option == -1){
				newLike.setLike_type(-1);
			}
			subject.addLike(newLike);
			likeDAO.save(newLike);

			Integer likes = subject.countNoLikes();
			Integer unlikes = subject.countNoUnlikes();

			retorno[0] = likes;
			retorno[1] = unlikes;

			return retorno;
		} else if (user == null) {
			throw new NotExistentUserException("Usuário inexistente.");	
		} else if (subject == null) {
			throw new NotExistentDisciplineException("Disciplina inexistente.");
		} else {
			throw new ServletException("Usuário e disciplinas inexistentes.");
		}
	}
	
	public Like findById(long id) {
		return likeDAO.findById(id);
	}

}
