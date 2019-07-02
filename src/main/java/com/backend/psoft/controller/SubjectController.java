package com.backend.psoft.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.backend.psoft.model.SubjectProfile;
import com.backend.psoft.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.psoft.exception.subjects.ExistingDisciplineException;
import com.backend.psoft.exception.subjects.NonExistentDisciplineException;
import com.backend.psoft.exception.users.NonExistentUserException;
import com.backend.psoft.exception.users.UserOfflineException;
import com.backend.psoft.model.Subject;
import com.backend.psoft.service.SubjectService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/subjects")
@RestController
public class SubjectController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SubjectService subjectService;
	
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	@ApiOperation(value = "Exibe todas as disciplinas da base de dados.")
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
	@ApiOperation(value = "Procura por disciplina(s) a partir de uma substring.")
	public ResponseEntity<List<Subject>> getBySubString(@PathVariable String subString) {
		List<Subject> subjects = subjectService.getSubjectBySubString(subString);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/searchId/{id}")
	@ApiOperation(value = "Obtém os dados de uma disciplina a partir do seu identificador único.")
	public ResponseEntity<SubjectProfile> getSubject(@PathVariable long id, @RequestHeader(value = "Authorization") String token) 
			throws NonExistentDisciplineException, UserOfflineException {
		
		Subject subject = subjectService.findById(id);
		if(subject == null) {
			throw new NonExistentDisciplineException("Disciplina inexistente na base de dados.");
		}
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if(emailUser == null) {
			throw new UserOfflineException("Usuário não logado!");
		}
		SubjectProfile ret = subjectService.getPerfilSubject(id, emailUser);
		return new ResponseEntity<SubjectProfile>(ret, HttpStatus.OK);
	}
	
	@PostMapping(value = "/")
	@ResponseBody
	@ApiOperation(value = "Cadastra uma nova disciplina.")
	public ResponseEntity<Subject> create(@RequestBody Subject subject) throws ExistingDisciplineException {
		Subject verifier = subjectService.findByName(subject.getSubjectName());
		if(verifier == null) {
			return new ResponseEntity<Subject>( subjectService.create(subject), HttpStatus.CREATED );			
		}
		throw new ExistingDisciplineException("Erro ao cadastrar! Disciplina já cadastrada na base de dados.");
	}

	// Metodo que efetua o cadastro das disciplinas atravez de um array de disciplinas
	// Abel Antunes de Lima Neto
	@PostMapping(value = "/fromList/")
	@ResponseBody
	@ApiOperation(value = "Cadastra todas as disciplinas do UCDB em uma única requisição.")
	public ResponseEntity<List<Subject>> createForList(@RequestBody Subject[] subjects) {
		return new ResponseEntity<List<Subject>>( subjectService.createForList(subjects), HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta uma disciplina da base de dados a partir do seu identificador.")
	public ResponseEntity<Subject> delete(@PathVariable long id) throws NonExistentDisciplineException {
		Subject subject = subjectService.findById(id);
		if(subject == null) {
			throw new NonExistentDisciplineException("Erro ao deletar! Disciplina inexistente na base de dados.");
		}
		subjectService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
