package com.backend.psoft.service;

import org.springframework.stereotype.Service;

import com.backend.psoft.dao.ProfileDAO;
import com.backend.psoft.model.Profile;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class ProfileService {
	
	private final ProfileDAO profileDAO;
	
	public ProfileService(ProfileDAO profileDAO) {
		this.profileDAO = profileDAO;
	}
	
	public Profile create(Profile profile) {
		return this.profileDAO.save(profile);
	}
	
	public Profile findById(long id) {
		return this.profileDAO.findById(id);
	}

}
