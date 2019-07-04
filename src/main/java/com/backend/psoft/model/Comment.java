package com.backend.psoft.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Date data = Calendar.getInstance().getTime();
	
	private long id_subject;
	
	private String user_email;
	
	private String comment_msg;

	private String user_name;

	private long comment_parent;

	@Column
	@ElementCollection(targetClass=Subject.class)
	@OneToMany
	private List<Comment> comments_resp;

	public Comment() {
		
	}
	
	public Comment(long id, long id_subject, String comment_msg, long comment_parent) {
		this.id = id;
		this.id_subject = id_subject;
		this.comment_msg = comment_msg;
		this.comment_parent = comment_parent;
		this.comments_resp = new ArrayList<Comment>();
	}
	
	public Comment(long id_subject,  String comment_msg) {
		this.id_subject = id_subject;
		this.comment_msg = comment_msg;
		this.comments_resp = new ArrayList<Comment>();
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
	
	public long getCommentParent() {
		return comment_parent;
	}
	
	public void setCommentParent(long commentId) {
		this.comment_parent = commentId;
	}

	public String getUser_name() {
		return this.user_name;
	}

	public void setUser_name(String newName) {
		this.user_name = newName;
	}

	public List<Comment> getComments_resp () {
		return this.comments_resp;
	}

	/*
	 * Método que adiciona uma resposta a um comentário
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public void addCommentResp (Comment resp) {
		this.comments_resp.add(resp);
	}

	/*
	 * Método que remove uma resposta de um comentário
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public void deleteCommentResp (Comment resp) {
		this.comments_resp.remove(resp);
	}
	
}
