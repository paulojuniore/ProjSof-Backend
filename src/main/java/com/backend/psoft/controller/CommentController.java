package com.backend.psoft.controller;

import javax.servlet.ServletException;

import com.backend.psoft.exception.users.UserOfflineException;
import com.backend.psoft.model.SubjectProfile;
import com.backend.psoft.service.LoginService;
import com.backend.psoft.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.psoft.model.Comment;
import com.backend.psoft.service.CommentService;
import java.util.List;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/comments")
@RestController
public class CommentController {
	
	private CommentService commentService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private LoginService loginService;

	/*
	 * Abel Antunes de Lima Neto - 117210287
	 *
	 * O método recebe o token do usuário e a partir dele identifica qual o usuário em questão
	 */
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@CrossOrigin
	@PostMapping("/postComment/")
	@ApiOperation(value = "Posta um comentário em uma disciplina.")
	ResponseEntity<SubjectProfile> postComment(@RequestBody Comment comment, @RequestHeader(value = "Authorization") String token) throws ServletException {

		//Confirma se um usuario está logado
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if(emailUser == null) {
			throw new UserOfflineException("Usuário não logado!");
		}
		commentService.create(comment, emailUser);
		SubjectProfile resp = subjectService.getPerfilSubject(comment.getId_subject(), comment.getUser_email());
		return new ResponseEntity<SubjectProfile>(resp, HttpStatus.CREATED);
	}

	/*
	 * Abel Antunes de Lima Neto - 117210287
	 *
	 * O método recebe o token do usuário e a partir dele identifica qual o usuário em questão
	 */
	@CrossOrigin
	@PostMapping("/postCommentOfAnswer/{id}")
	ResponseEntity<SubjectProfile> postCommentOfAnswer(@PathVariable long id, @RequestBody Comment comment, @RequestHeader(value = "Authorization") String token)
			throws UserOfflineException {
		//Confirma se um usuario está logado
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if(emailUser == null) {
			throw new UserOfflineException("Usuário não logado!");
		}

		commentService.createCommentOfAnswer(id, comment, emailUser);
		return new ResponseEntity<SubjectProfile>(subjectService.getPerfilSubject(comment.getId_subject(), comment.getUser_email()), HttpStatus.CREATED);
	}

	/*
	 * Método que remove um comentário a partir de seu id,
	 * é necessário o token do usuário para confirmar se o comentário
	 * é dele.
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	@CrossOrigin
	@DeleteMapping("/deleteComment/{id}")
	@ApiOperation(value = "Remove um comentário a partir do seu identificador único.")
	ResponseEntity<SubjectProfile> deleteComment(@PathVariable long id, @RequestHeader(value = "Authorization") String token) throws ServletException {
		Comment comment = commentService.findById(id);
		if(comment == null) {
			throw new ServletException("Comentário inexistente!");
		}
		//Confirma se um usuario está logado
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if(emailUser == null) {
			throw new UserOfflineException("Usuário não logado!");
		}

		commentService.deleteComment(comment, emailUser);
		return new ResponseEntity<SubjectProfile>(subjectService.getPerfilSubject(comment.getId_subject(), emailUser), HttpStatus.OK);
	}

	/**
	 * 
	 * Pegar as resposta de um comentário, dado seu ID
	 * 
	 * @param id
	 * @param token
	 * @return
	 * @throws ServletException
	 */

	@CrossOrigin
	@GetMapping("/getCommentId/{id}")
	ResponseEntity<List<Comment>> getCommentAnswer(@PathVariable long id, @RequestHeader(value = "Authorization") String token) throws ServletException{
		Comment comment = commentService.findById(id);

		if(comment == null){
			throw new ServletException("Comentário inexistente");
		}

		//Confirma se um usuario está logado
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if(emailUser == null) {
			throw new UserOfflineException("Usuário não logado!");
		}


		return new ResponseEntity<List<Comment>>(comment.getComments_resp(), HttpStatus.OK);
	}


}
