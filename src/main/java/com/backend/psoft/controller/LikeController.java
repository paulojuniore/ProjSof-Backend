package com.backend.psoft.controller;

import javax.servlet.ServletException;
import com.backend.psoft.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.psoft.exception.subjects.NotExistentDisciplineException;
import com.backend.psoft.exception.users.NotExistentUserException;
import com.backend.psoft.exception.users.UserOfflineException;
import com.backend.psoft.model.Like;
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

	@Autowired
	private LikeService likeService;
	@Autowired
	private LoginService loginService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	/*
	 * Abel Antunes de Lima Neto - 117210287
	 *
	 * O método recebe o token do usuário e a partir dele identifica qual o usuário em questão
	 */
	@CrossOrigin
	@PostMapping("/setLike/")
	@ApiOperation(value = "Requisição para efetuar um like/dislike em uma determinada disciplina.")
	ResponseEntity<LikeResponse> create(@RequestBody Like like, @RequestHeader(value = "Authorization") String token) 
			throws UserOfflineException, NotExistentUserException, NotExistentDisciplineException, ServletException {
		String emailUser = loginService.getEmailUserLogin(token.split("Bearer ")[1]);
		if (emailUser == null) {
			throw new UserOfflineException("Usuário não logado.");
		}
		like.setEmailUser(emailUser);
		Integer[] likesAtual = likeService.create(like);
		LikeResponse response = new LikeResponse(likesAtual[0], likesAtual[1]);
		return new ResponseEntity<LikeResponse>(response, HttpStatus.OK);
	}

	private class LikeResponse {
		
		private Integer likes;
		private Integer unlikes;

		public LikeResponse(Integer likes, Integer unlikes) {
			this.setLikes(likes);
			this.setUnlikes(unlikes);
		}

		@SuppressWarnings("unused")
		public Integer getLikes() {
			return this.likes;
		}

		@SuppressWarnings("unused")
		public Integer getUnlikes() {
			return this.unlikes;
		}

		public void setLikes(Integer likes) {
			this.likes = likes;
		}

		public void setUnlikes(Integer unlikes) {
			this.unlikes = unlikes;
		}
		
	}
	
}
