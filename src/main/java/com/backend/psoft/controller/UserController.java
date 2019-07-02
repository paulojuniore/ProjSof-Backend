package com.backend.psoft.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.psoft.exception.users.NonExistentUserException;
import com.backend.psoft.model.User;
import com.backend.psoft.service.UserService;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/users")
@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation(value = "Exibe todos os usuários cadastrados na base de dados.")
	@GetMapping("/")
	public ResponseEntity<List<User>> listAll() {
		List<User> users = userService.list();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Pesquisa se um determinado email de usuário encontra-se cadastrado na base de dados.")
	@GetMapping(value = "/{email}")
	public ResponseEntity<User> getUser(@PathVariable String email) throws NonExistentUserException {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new NonExistentUserException("Usuário inexistente na base de dados.");
		}
		return new ResponseEntity<User>(userService.findByEmail(email), HttpStatus.OK);
	}

	/*
	 * Caminho que verifica um token e confirma o cadastro de um usuário
	 */
	@ApiOperation(value = "Verifica se o token que foi passado ao fazer cadastro é valido.")
	@GetMapping(value = "/verify/{token}") // Apagar os token depois.
	public String verifyToken(@PathVariable String token) {
		if (userService.verificaToken(token)) {
			return "Deu certo"; // Mudar retorno.
		}
		return "Deu errado cara"; // Mudar retorno
	}
	
	// Cadastro de um novo usuário.
	@CrossOrigin
	@PostMapping(value = "/")
	@ResponseBody
	@ApiOperation(value = "Cadastra um novo usuário na base de dados.")
	public ResponseEntity<User> create(@RequestBody User user) {
		userService.create(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// Deletando um usuário já existente
	@ApiOperation(value = "Remove um usuário que encontra-se cadastrado a partir do seu email.")
	@DeleteMapping("/{email}")
	public ResponseEntity<User> delete(@PathVariable String email) throws NonExistentUserException {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new NonExistentUserException("Erro ao deletar! E-mail inexistente na base de dados.");
		}
		userService.deleteByEmail(email);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
}
