package com.backend.psoft.controller;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.model.Like;
import com.backend.psoft.model.Subject;
import com.backend.psoft.model.User;
import com.backend.psoft.service.LikeService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/actions")
@RestController
public class LikeController {
	
	private LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@PostMapping("/setLike/")
	@ApiOperation(value = "Requisição para efetuar um like/dislike em uma determinada disciplina.")
	ResponseEntity<Like> create(@RequestBody Like like) throws ServletException {
		return new ResponseEntity<Like>(likeService.create(like), HttpStatus.OK);
	}
	
}
