package com.backend.psoft.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date data = Calendar.getInstance().getTime();
	
	private long id_subject;
	
	private String user_email;
	
	private String comment_msg;
	
	@OneToMany
	private List<Comment> comment_answer = new ArrayList<Comment>();
	
	public Comment() {
		
	}
	
	public Comment(long id, long id_subject, String user_email, String comment_msg, List<Comment> comment_answer) {
		this.id = id;
		this.id_subject = id_subject;
		this.user_email = user_email;
		this.comment_msg = comment_msg;
		this.comment_answer = comment_answer;
	}
	
	public Comment(long id_subject, String user_email, String comment_msg) {
		this.id_subject = id_subject;
		this.user_email = user_email;
		this.comment_msg = comment_msg;
	}
	
	public void addCommentOfAnswer(Comment comment) {
		this.comment_answer.add(comment);
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

	public List<Comment> getComment_answer() {
		return comment_answer;
	}

	public void setComment_answer(List<Comment> comment_answer) {
		this.comment_answer = comment_answer;
	}
	
}
