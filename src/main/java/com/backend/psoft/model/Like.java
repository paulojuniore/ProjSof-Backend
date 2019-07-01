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

	private String emailUser, nameUser;
	
	private long idSubject;

	// like_type: 1 ou -1: 0 = dislike, 1 = like
	private Integer like_type;
	
	public Like() {
		
	}
	
	public Like(long id, long idSubject, Integer like_type) {
		this.id = id;
		this.idSubject = idSubject;
		this.like_type = like_type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmailUser() {
		return emailUser;
	}
	
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public long getIdSubject() {
		return this.idSubject;
	}

	public void setIdSubject(long subjectId) {
		this.idSubject = subjectId;
	}
	
	public String getNameUser() {
		return this.nameUser;
	}
	
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	public Integer getLike_type() {
		return like_type;
	}

	public void setLike_type(Integer like_type) {
		this.like_type = like_type;
	}

}
