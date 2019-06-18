package com.backend.psoft.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String subjectName;

	@Column(name = "likes")
	@ElementCollection(targetClass=User.class)
	private List<User> likes;

	@Column(name = "deslikes")
	@ElementCollection(targetClass=User.class)
	private List<User> deslikes;
	
	public Subject() {
		
	}
	
	public Subject(long id, String subjectName) {
		this.id = id;
		this.subjectName = subjectName;
	}
	
	public Subject(long id, String subjectName, List<User> likes, List<User> deslikes) {
		this.id = id;
		this.subjectName = subjectName;
		this.likes = likes;
		this.deslikes = deslikes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
