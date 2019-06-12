package com.backend.psoft.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.psoft.model.User;
import com.backend.psoft.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> listAll() {
		List<User> users = userService.list();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{email}")
	public ResponseEntity<User> getUser(@PathVariable String email) throws ServletException {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new ServletException("Usuário inexistente na base de dados");
		}
		return new ResponseEntity<User>( userService.findByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) throws ServletException {
		return new ResponseEntity<User>( userService.create(user), HttpStatus.CREATED );
	}
	
}
