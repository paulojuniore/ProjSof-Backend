package com.backend.psoft.service;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.model.User;

@Service
public class UserService {
	
	private final UserDAO userDAO;
	
	public UserService (UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User create(User user) throws ServletException {
		User u = userDAO.findByEmail(user.getEmail());
		if(u != null) {
			throw new ServletException("Usuário já existente!");
		}
		return userDAO.save(user);
	}
	
	public User findByEmail(String login) {
		return userDAO.findByEmail(login);
	}
	
	public List<User> list() {
		return this.userDAO.findAll();
	}
	
}
