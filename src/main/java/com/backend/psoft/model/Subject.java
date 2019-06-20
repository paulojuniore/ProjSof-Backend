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
	
	@Column
	@ElementCollection(targetClass=Subject.class)
	private List<Like> likes;

	public Subject() {
		
	}

	public Subject(long id, String subjectName, List<Like> likes) {
		this.id = id;
		this.subjectName = subjectName;
		this.likes = likes;
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
	
	public List<Like> getLikes() {
		return likes;
	}
	
	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

}
