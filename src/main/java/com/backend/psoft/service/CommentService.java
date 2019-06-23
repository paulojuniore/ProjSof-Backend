package com.backend.psoft.service;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.psoft.dao.CommentDAO;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
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
	
	public Comment create(Comment comment) throws ServletException {
		Subject subject = subjectDAO.findById(comment.getId_subject());
		User user = userDAO.findByEmail(comment.getUser_email()); 
		if(subject != null && user != null) {
			subject.addComment(comment);
			return commentDAO.save(comment);			
		} else if(subject == null) {
			throw new ServletException("Disciplina inexistente!!");			
		} else {
			throw new ServletException("Usuário inexistente!!");
		}
	}
	
	public Comment findById(long id) {
		return commentDAO.findById(id);
	}
	
	public void deleteById(long id) {
		commentDAO.deleteById(id);
	}

}
