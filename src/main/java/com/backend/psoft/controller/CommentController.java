package com.backend.psoft.controller;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.psoft.model.Comment;
import com.backend.psoft.service.CommentService;

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
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/postComment/")
	@ApiOperation(value = "Posta um comentário em uma disciplina.")
	ResponseEntity<Comment> postComment(@RequestBody Comment comment) throws ServletException {
		return new ResponseEntity<Comment>(commentService.create(comment), HttpStatus.CREATED);
	}
	
	@PostMapping("/postCommentOfAnswer/{id}")
	ResponseEntity<Comment> postCommentOfAnswer(@PathVariable long id, @RequestBody Comment comment) throws ServletException {
		return new ResponseEntity<Comment>(commentService.createCommentOfAnswer(id, comment), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteComment/{id}")
	@ApiOperation(value = "Remove um comentário a partir do seu identificador único.")
	ResponseEntity<Comment> deleteComment(@PathVariable long id) throws ServletException {
		Comment comment = commentService.findById(id);
		if(comment == null) {
			throw new ServletException("Comentário inexistente!");
		}
		commentService.deleteById(id);
		return new ResponseEntity<Comment>(HttpStatus.OK);
	}

}
