package com.backend.psoft.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Entity
@Table(name = "profile")
public class Profile {
	
	@Id
	private long id;
	
	@Column
	@ElementCollection(targetClass=User.class)
	private List<User> likes;

	@Column
	@ElementCollection(targetClass=User.class)
	private List<User> deslikes;
	
	public Profile() {
		
	}
	
	public Profile(long id, List<User> likes, List<User> deslikes) {
		this.id = id;
		this.likes = likes;
		this.deslikes = deslikes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}

	public List<User> getDeslikes() {
		return deslikes;
	}

	public void setDeslikes(List<User> deslikes) {
		this.deslikes = deslikes;
	}

}
