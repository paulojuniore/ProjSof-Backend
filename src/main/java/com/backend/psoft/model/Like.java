package com.backend.psoft.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long user_id;
	
	private boolean like_type;
	
	public Like() {
		
	}
	
	public Like(long id, long user_id, boolean like_type) {
		this.id = id;
		this.user_id = user_id;
		this.like_type = like_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getUser_id() {
		return user_id;
	}
	
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public boolean getLike_type() {
		return like_type;
	}

	public void setLike_type(boolean like_type) {
		this.like_type = like_type;
	}

}
