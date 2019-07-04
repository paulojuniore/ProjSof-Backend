package com.backend.psoft.service;

import java.util.Date;

import com.backend.psoft.exception.login.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.psoft.dao.CommentDAO;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.exception.comment.CommentParentInexistentException;
import com.backend.psoft.exception.subjects.NotExistentDisciplineException;
import com.backend.psoft.exception.users.NotExistentUserException;
import com.backend.psoft.model.Comment;
import com.backend.psoft.model.Subject;
import com.backend.psoft.model.User;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 * Abel Antunes de Lima Neto - 117210287
 *
 */
@Service
public class CommentService {
	

	@Autowired
	private SubjectDAO subjectDAO;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public CommentService(CommentDAO commentDAO) {

	}
	/*
	 * Método que cria um novo comentário e o armazena no banco de dados
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public Comment create(Comment comment, String emailUser) throws NotExistentDisciplineException, NotExistentUserException {
		Subject subject = subjectDAO.findById(comment.getId_subject());
		User user = userDAO.findByEmail(emailUser);
		if(subject != null && user != null) {
			String nameUser = (user.getFirstName() + " " + user.getLastName());
			comment.setUser_name(nameUser);
			comment.setUser_email(emailUser);
			subject.addComment(comment);
			return commentDAO.save(comment);			
		} else if(subject == null) {
			throw new NotExistentDisciplineException("Disciplina inexistente!");			
		} else {
			throw new NotExistentDisciplineException("Usuário inexistente!");
		}
	}

	/*
	 * Método que cria uma resposta para um comentário e armazena no banco de dados.
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public Comment createCommentOfAnswer(long id, Comment comment, String emailUser)
			throws NotExistentDisciplineException, NotExistentUserException {
		Comment commentAux = commentDAO.findById(id);
		Subject subject = subjectDAO.findById(comment.getId_subject());
		Comment commentInSub = subject.getComment(id);
		User user = userDAO.findByEmail(emailUser);
		if(commentAux != null && subject != null && user != null && commentInSub != null) {
			String nameUser = (user.getFirstName() + " " + user.getLastName());
			comment.setUser_name(nameUser);
			comment.setCommentParent(id);
			comment.setData();
			comment.setUser_email(emailUser);
			comment.setId_subject(subject.getId());
			commentAux.addCommentResp(comment);
			return commentDAO.save(comment);

		} else if(commentAux == null) {
			throw new CommentParentInexistentException("Comentário inexistente!");
		} else if(subject == null) {
			throw new NotExistentDisciplineException("Disciplina inexistente!");
		} else if(commentInSub == null) {
			throw new CommentParentInexistentException("Esse comentario ja foi apagado.");
		} else {
			throw new NotExistentUserException("Usuário inexistente!");
		}
	}

	/*
	 * Método que busca um comentario por seu id.
	 */
	public Comment findById(long id) {
		return commentDAO.findById(id);
	}

	/*
	 * Método que drleta um comentário a partir de seu "clone"
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public void deleteComment(Comment comment, String emailUser) {
		Subject subject = subjectService.findById(comment.getId_subject());
		if (subject == null) {
			throw new NotExistentDisciplineException("Erro ao encontrar disciplina.");
		}

		String emailComment = comment.getUser_email();
		if (!emailComment.equals(emailUser)) {
			throw new InvalidTokenException("Esse comentario possui a outro usuario.");
		}
		if (!(comment.getCommentParent() > 0)) {
			subject.deleteComment(comment);
			subjectDAO.save(subject);
		} else {
			Comment commentPai = subject.getComment(comment.getCommentParent());
			commentPai.deleteCommentResp(comment);
			commentDAO.save(commentPai);
		}
	}

}
