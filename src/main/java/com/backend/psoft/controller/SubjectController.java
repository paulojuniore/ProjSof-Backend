package com.backend.psoft.controller;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	/** 
	 * @author Hércules Rodrigues - herculesra - 117210908
	 * 
	 * Rota para retornar todas as disciplinas referentes à sub string recebida no request.
	*/
	@CrossOrigin
	@GetMapping("/search/{subString}")
	public ResponseEntity<List<Subject>> getBySubString(@PathVariable String subString) throws ServletException{
		List<Subject> subjects = subjectService.getSubjectBySubString(subString);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/searchId/{id}")
	public ResponseEntity<Subject> getSubject(@PathVariable long id) throws ServletException {
		Subject subject = subjectService.findById(id);
		if(subject == null) {
			throw new ServletException("Disciplina inexistente na base de dados.");
		}
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<Subject> create(@RequestBody Subject subject) throws ServletException {
		Subject verifier = subjectService.findByName(subject.getSubjectName());
		if(verifier == null) {
			return new ResponseEntity<Subject>( subjectService.create(subject), HttpStatus.CREATED );			
		}
		throw new ServletException("Erro ao cadastrar! Disciplina já cadastrada na base de dados.");
	}

	// Metodo que efetua o cadastro das disciplinas atravez de um array de disciplinas
	@PostMapping(value = "/fromList/")
	@ResponseBody
	public ResponseEntity<List<Subject>> createForList(@RequestBody Subject[] subjects) throws ServletException {
		return new ResponseEntity<List<Subject>>( subjectService.createForList(subjects), HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Subject> delete(@PathVariable long id) throws ServletException {
		Subject subject = subjectService.findById(id);
		if(subject == null) {
			throw new ServletException("Erro ao deletar! Disciplina inexistente na base de dados.");
		}
		subjectService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
