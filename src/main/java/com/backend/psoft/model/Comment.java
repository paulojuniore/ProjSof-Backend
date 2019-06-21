package com.backend.psoft.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date data;
	
	private long id_subject;
	
	private String user_email;
	
	private String comment_msg;
	
	@OneToOne
	private Comment comment_answer;
	
	public Comment() {
		
	}
	
	public Comment(long id, Date data, long id_subject, String user_email, String comment_msg, Comment comment_answer) {
		this.id = id;
		this.data = data;
		this.id_subject = id_subject;
		this.user_email = user_email;
		this.comment_msg = comment_msg;
		this.comment_answer = comment_answer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getId_subject() {
		return id_subject;
	}

	public void setId_subject(long id_subject) {
		this.id_subject = id_subject;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getComment_msg() {
		return comment_msg;
	}

	public void setComment_msg(String comment_msg) {
		this.comment_msg = comment_msg;
	}

	public Comment getComment_answer() {
		return comment_answer;
	}

	public void setComment_answer(Comment comment_answer) {
		this.comment_answer = comment_answer;
	}
	
}
