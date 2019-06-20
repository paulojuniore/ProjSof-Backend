package com.backend.psoft.controller;

import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.dao.UserDAO;
import com.backend.psoft.model.Like;
import com.backend.psoft.model.Subject;
import com.backend.psoft.model.User;
import com.backend.psoft.service.LikeService;

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
	
	@PostMapping("/setLike/{id}/{email}/{option}")
	ResponseEntity<Like> create(@PathVariable long id, @PathVariable String email, @PathVariable String option) throws ServletException {
		return new ResponseEntity<Like>(likeService.create(id, email, option), HttpStatus.OK);
	}
	
}
