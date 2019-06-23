package com.backend.psoft.controller;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.psoft.model.Comment;
import com.backend.psoft.service.CommentService;

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
	ResponseEntity<Comment> postComment(@RequestBody Comment comment) throws ServletException {
		return new ResponseEntity<Comment>(commentService.create(comment), HttpStatus.CREATED);
	}

}
