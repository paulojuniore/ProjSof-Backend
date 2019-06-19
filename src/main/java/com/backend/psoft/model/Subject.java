package com.backend.psoft.model;

import javax.persistence.Entity;
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
	private String subjectName;

	public Subject() {
		
	}

	public Subject(String subjectName) {

		this.subjectName = subjectName;
	}


	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


}
