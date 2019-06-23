package com.backend.psoft.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany
	private List<Like> likes = new ArrayList<Like>();
	
	@Column
	@ElementCollection(targetClass=Subject.class)
	@OneToMany
	private List<Comment> comments = new ArrayList<Comment>();

	public Subject() {
		
	}

	public Subject(long id, String subjectName, List<Like> likes, List<Comment> comments) {
		this.id = id;
		this.subjectName = subjectName;
		this.likes = likes;
		this.comments = comments;
	}
	
	public void addLike(Like like) {
		this.likes.add(like);
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
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
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
