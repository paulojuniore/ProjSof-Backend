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

import com.backend.psoft.model.Subject;
import com.backend.psoft.service.SubjectService;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/subjects")
@RestController
public class SubjectController {
	
	private SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Subject>> listAll() {
		List<Subject> subjects = subjectService.list();
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Subject> getUser(@PathVariable long id) throws ServletException {
		Subject subject = subjectService.findById(id);
		if(subject == null) {
			throw new ServletException("Disciplina inexistente na base de dados.");
		}
		return new ResponseEntity<Subject>( subjectService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody Subject subject) throws ServletException {
		return new ResponseEntity<Subject>( subjectService.create(subject), HttpStatus.CREATED );
	}
	
}
