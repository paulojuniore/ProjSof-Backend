package com.backend.psoft.controller;

import javax.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.psoft.model.Like;
import com.backend.psoft.service.LikeService;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/subjects/actions")
@RestController
public class LikeController {
	
	private LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	/*
	@PostMapping("/setLike")
	ResponseEntity<Like> create(@PathVariable id, @PathVariable email, @PathVariable option) throws ServletException {
		
	}
	*/
	
	

}
