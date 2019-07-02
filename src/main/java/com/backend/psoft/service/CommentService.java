package com.backend.psoft.service;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

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
 */
@Service
public class CommentService {
	
	private CommentDAO commentDAO;
	
	@Autowired
	private SubjectDAO subjectDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	public Comment create(Comment comment) throws NonExistentDisciplineException, NonExistentUserException {
		Subject subject = subjectDAO.findById(comment.getId_subject());
		User user = userDAO.findByEmail(comment.getUser_email()); 
		if(subject != null && user != null) {
			subject.addComment(comment);
			return commentDAO.save(comment);			
		} else if(subject == null) {
			throw new NonExistentDisciplineException("Disciplina inexistente!");			
		} else {
			throw new NonExistentDisciplineException("Usuário inexistente!");
		}
	}
	
	public Comment createCommentOfAnswer(long id, Comment comment) 
			throws NonExistentDisciplineException, NonExistentUserException {
		Comment commentAux = commentDAO.findById(id);
		Subject subject = subjectDAO.findById(comment.getId_subject());
		User user = userDAO.findByEmail(comment.getUser_email());
		if(commentAux != null && subject != null && user != null) {
			System.out.println("ENTREI AQUI!!!");
			comment.setCommentParent(commentAux);
			comment.setData(new Date());
			comment.setUser_email(user.getEmail());
			comment.setId_subject(subject.getId());

			commentAux.addCommentOfAnswer(commentAux);
			commentDAO.save(commentAux);
			
			return commentDAO.save(comment);
		} else if(commentAux == null) {
			throw new CommentParentInexistentException("Comentário inexistente!");
		} else if(subject == null) {
			throw new NonExistentDisciplineException("Disciplina inexistente!");
		} else {
			throw new NonExistentUserException("Usuário inexistente!");
		}
	}
	
	public Comment findById(long id) {
		return commentDAO.findById(id);
	}
	
	public void deleteById(long id) {
		commentDAO.deleteById(id);
	}

}
