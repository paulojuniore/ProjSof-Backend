package com.backend.psoft.service;

import java.util.HashMap;
import java.util.List;
import com.backend.psoft.util.EmailBoasVindas;
import com.backend.psoft.util.EnviaEmail;
import com.backend.psoft.util.Mensagem;
import com.backend.psoft.util.VerificaCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.exception.users.UserAlreadyExistsException;
import com.backend.psoft.model.User;

@Service
public class UserService {
	
	private final UserDAO userDAO;
	
	private HashMap <String, User> tokens;

	@Autowired
	private EnviaEmail enviaEmail;
	
	@Autowired
	private VerificaCadastro verifica;

	public UserService (UserDAO userDAO) {
		this.userDAO = userDAO;
		this.tokens = new HashMap<String, User>();
	}

	public void create(User user) throws UserAlreadyExistsException {
		User u = userDAO.findByEmail(user.getEmail());
		if (u != null) {
			throw new UserAlreadyExistsException("Usuário já existente!");
		} else {
			// Envia um E-mail de boas vindas aos novos usuarios.
			String nome = user.getFirstName() + " " + user.getLastName();
			// Gerando token para confirmar cadastro.
			String token = verifica.geraToken(user.getEmail());
			this.tokens.put(token, user);
			EmailBoasVindas emailBoasVindas = new EmailBoasVindas(nome, user.getEmail(), token);
			Mensagem mensagemEnvio = emailBoasVindas.converteMensagem();
			enviaEmail.enviar(mensagemEnvio);
		}
	}

	/*
	 * Cadastra um usuário a partir do token que ele recebeu no e-mail.
	 */
	private void cadastraUser(String token) {
		User user = tokens.get(token);
		if (user != null) {
			userDAO.save(user);
		}
	}

	// Método que verifica se um token é pertencente a algum usuário.
	public Boolean verificaToken(String token) {
		if (tokens.containsKey(token)) {
			cadastraUser(token);
			tokens.remove(token);
			return true;
		}
		return false;
	}

	public User findByEmail(String login) {
		return userDAO.findByEmail(login);
	}
	
	public void deleteByEmail(String email) {
		userDAO.deleteByEmail(email);
	}
	
	public List<User> list() {
		return this.userDAO.findAll();
	}
	
}
