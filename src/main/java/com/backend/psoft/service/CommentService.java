package com.backend.psoft.service;

import java.util.Date;

import com.backend.psoft.exception.login.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.psoft.dao.CommentDAO;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.exception.comment.CommentParentInexistentException;
import com.backend.psoft.exception.subjects.NonExistentDisciplineException;
import com.backend.psoft.exception.users.NonExistentUserException;
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
	
	public Comment create(Comment comment, String emailUser) throws NonExistentDisciplineException, NonExistentUserException {
		Subject subject = subjectDAO.findById(comment.getId_subject());
		User user = userDAO.findByEmail(emailUser);
		if(subject != null && user != null) {
			comment.setUser_email(emailUser);
			subject.addComment(comment);
			return commentDAO.save(comment);			
		} else if(subject == null) {
			throw new NonExistentDisciplineException("Disciplina inexistente!");			
		} else {
			throw new NonExistentDisciplineException("Usuário inexistente!");
		}
	}
	
	public Comment createCommentOfAnswer(long id, Comment comment, String emailUser)
			throws NonExistentDisciplineException, NonExistentUserException {
		Comment commentAux = commentDAO.findById(id);
		Subject subject = subjectDAO.findById(comment.getId_subject());
		Comment commentInSub = subject.getComment(id);
		User user = userDAO.findByEmail(emailUser);
		if(commentAux != null && subject != null && user != null && commentInSub != null) {
			comment.setCommentParent(id);
			comment.setData(new Date());
			comment.setUser_email(emailUser);
			comment.setId_subject(subject.getId());
			commentAux.addCommentResp(comment);
			return commentDAO.save(comment);

		} else if(commentAux == null) {
			throw new CommentParentInexistentException("Comentário inexistente!");
		} else if(subject == null) {
			throw new NonExistentDisciplineException("Disciplina inexistente!");
		} else if(commentInSub == null) {
			throw new CommentParentInexistentException("Esse comentario ja foi apagado.");
		} else {
			throw new NonExistentUserException("Usuário inexistente!");
		}
	}
	
	public Comment findById(long id) {
		return commentDAO.findById(id);
	}

	public void deleteComment(Comment comment, String emailUser) {
		Subject subject = subjectService.findById(comment.getId_subject());
		if (subject == null) {
			throw new NonExistentDisciplineException("Erro ao encontrar disciplina.");
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
