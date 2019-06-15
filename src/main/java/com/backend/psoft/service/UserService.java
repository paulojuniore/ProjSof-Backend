package com.backend.psoft.service;
import java.util.List;

import javax.servlet.ServletException;

import com.backend.psoft.util.EmailBoasVindas;
import com.backend.psoft.util.EnviaEmail;
import com.backend.psoft.util.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.model.User;

@Service
public class UserService {
	
	private final UserDAO userDAO;

	@Autowired
	private EnviaEmail enviaEmail;

	public UserService (UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User create(User user) throws ServletException {
		User u = userDAO.findByEmail(user.getEmail());
		if (u != null) {
			throw new ServletException("Usuário já existente!");
		} else {
			// Envia um E-mail de boas vindas aos novos usuarios.
			String nome = user.getFirstName() + " " + user.getLastName();
			EmailBoasVindas emailBoasVindas = new EmailBoasVindas(nome, user.getEmail());
			Mensagem mensagemEnvio = emailBoasVindas.converteMensagem();
			System.out.println(mensagemEnvio.getCorpo());
			enviaEmail.enviar(mensagemEnvio);
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
