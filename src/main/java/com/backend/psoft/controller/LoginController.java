package com.backend.psoft.controller;

import java.util.Date;
import com.backend.psoft.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.backend.psoft.exception.login.InvalidPasswordException;
import com.backend.psoft.exception.users.NotExistentUserException;
import com.backend.psoft.model.User;
import com.backend.psoft.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/auth")
@RestController
public class LoginController {
	
	private final String TOKEN_KEY = "psoft";

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@CrossOrigin
	@PostMapping("/login")
	@ApiOperation(value = "Efetua o login/autenticação de um usuário.")
	public LoginResponse authenticate(@RequestBody User user) 
			throws InvalidPasswordException, NotExistentUserException {
		// Recupera o usuário
		User authUser = userService.findByEmail(user.getEmail());
		// Verificações
		if(authUser == null) {
			throw new NotExistentUserException("Usuário não existe!");
		}
		if(!authUser.getPassword().equals(user.getPassword())) {
			throw new InvalidPasswordException("Senha inválida!");
		}
		String token = Jwts.builder()
				.setSubject(authUser.getEmail())
				.signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 300 * 60 * 1000))
				.compact();
		loginService.addLogin(authUser.getEmail(), token);
		return new LoginResponse(token);
	}

	// Responsável pelo token de acesso
	private class LoginResponse {
		private String token;
		
		public LoginResponse(String token) {
			this.setToken(token);
		}

		@SuppressWarnings("unused")
		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}

}
