package com.backend.psoft.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.psoft.service.ProfileService;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@RequestMapping("/profiles")
@RestController
public class ProfileController {
	
	private ProfileService profileService;
	
	private ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

}
