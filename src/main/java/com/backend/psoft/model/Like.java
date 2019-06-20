package com.backend.psoft.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Paulo Mendes da Silva Júnior
 *
 */
@Entity
@Table(name = "user_like")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Subject subject;
	
	// like_type: 0 ou 1, "dislike" ou "like"
	private boolean like_type;
	
	public Like() {
		
	}
	
	public Like(long id, User user, Subject subject, boolean like_type) {
		this.id = id;
		this.user = user;
		this.subject = subject;
		this.like_type = like_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public boolean getLike_type() {
		return like_type;
	}

	public void setLike_type(boolean like_type) {
		this.like_type = like_type;
	}

}
