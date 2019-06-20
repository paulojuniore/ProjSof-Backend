package com.backend.psoft.service;

import org.springframework.stereotype.Service;
import com.backend.psoft.dao.LikeDAO;
import com.backend.psoft.model.Like;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class LikeService {
	
	private final LikeDAO likeDAO;
	
	public LikeService(LikeDAO likeDAO) {
		this.likeDAO = likeDAO;
	}
	
	public Like create(Like like) {
		return likeDAO.save(like);
	}
	
	public Like findById(long id) {
		return likeDAO.findById(id);
	}

}
