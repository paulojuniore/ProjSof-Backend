package com.backend.psoft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "like")
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String user_id;
	
	private long subject_id;
	
	private String like_type;
	
	public Like() {
		
	}
	
	public Like(long id, String user_id, long subject_id, String like_type) {
		this.id = id;
		this.user_id = user_id;
		this.subject_id = subject_id;
		this.like_type = like_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public String getLike_type() {
		return like_type;
	}

	public void setLike_type(String like_type) {
		this.like_type = like_type;
	}

}
