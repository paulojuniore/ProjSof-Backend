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
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<Comment>();

	public Subject() {
		
	}

	public Subject(long id, String subjectName, List<Like> likes, List<Comment> comments) {
		this.id = id;
		this.subjectName = subjectName;
		this.likes = likes;
		this.comments = comments;
	}

	/*
	 * Abel Antunes de Lima Neto - 117210287
	 * Ao receber um like analisa se um usuario ainda nao deu like ou se
	 * esta mudando o tipo de like que foi dado.
	 */
	public void addLike(Like like) {
		String email = like.getEmailUser();
		Like likenInList = getLike(email);
		if (likenInList == null) {
			this.likes.add(like);
		} else if (likenInList.getLike_type() == like.getLike_type()) {
			this.likes.remove(likenInList);
		} else {
			likenInList.setLike_type(like.getLike_type());
		}

	}

	// Comparator para número de likes
	public int compareToLikes(Subject sub) {
		if (this.countNoLikes() < sub.countNoLikes()) {
			return 1;
		} else if (this.countNoLikes() > sub.countNoLikes()) {
			return -1;
		} else {
			return 0;
		}
	}

	// Comparator para número de dislikes
	public int compareToUnlikes(Subject sub) {
		if (this.countNoUnlikes() < sub.countNoUnlikes()) {
			return 1;
		} else if (this.countNoUnlikes() > sub.countNoUnlikes()) {
			return -1;
		} else {
			return 0;
		}
	}

	// Comparator para número de comentários
	public int compareToComents(Subject sub) {
		if (this.getNumComents() < sub.getNumComents()) {
			return 1;
		} else if (this.getNumComents() > sub.getNumComents()) {
			return -1;
		} else {
			return 0;
		}
	}
	
	// Comparator para proporção de likes/dislikes
	public int compareToProportionLikesAndDislikes(Subject sub) {
		if(this.countNoLikes() > 0 && this.countNoUnlikes() > 0
				&& sub.countNoLikes() > 0 && sub.countNoUnlikes() > 0) {
			int prop1 = this.countNoLikes() / (this.countNoLikes() + this.countNoUnlikes());
			int prop2 = sub.countNoLikes() / (sub.countNoLikes() + sub.countNoUnlikes());
			if (prop1 < prop2) {
				return 1;
			} else if (prop1 > prop2) {
				return -1;
			}
		}
		return 0;
	}

	public Like getLike(String email) {
		int count = 0;
		Like retorno = null;
		Boolean naoAchou = true;
		while (this.likes.size() > count && naoAchou) {
			Like like = this.likes.get(count);
			if (like.getEmailUser().equals(email)){
				retorno = like;
				naoAchou = false;
			}
			count ++;
		}
		return retorno;
	}
	
	public int countNoLikes() {
		int contaLikes = 0;
		for (Like like : this.likes) {
			if(like.getLike_type().equals(1)) {
				contaLikes++;
			}
		}
		return contaLikes;
	}
	
	public int countNoUnlikes() {
		int contaUnlikes = 0;
		for (Like like : this.likes) {
			if(like.getLike_type().equals(-1)) {
				contaUnlikes++;
			}
		}
		return contaUnlikes;
	}

	public void deleteComment(Comment comment) {
		this.comments.remove(comment);
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

	public Integer getNumComents() {
		return this.comments.size();
	}

	public Comment getComment(long idComment) {
		Integer indice = 0;
		Boolean naoEncontrou = true;
		Comment retorno = null;
		while (indice < this.comments.size() && naoEncontrou) {
			if (this.comments.get(indice).getId() == idComment) {
				retorno = this.comments.get(indice);
				naoEncontrou = false;
			}

			indice ++;
		}

		return retorno;
	}

	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
