package com.backend.psoft.service;

import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.model.Subject;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.List;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class SubjectService {
	
	private final SubjectDAO subjectDAO;
	
	
	public SubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	/**
	 * @author Hércules Rodrigues
	 */
	public List<Subject> getSubjectBySubString(String subString){
		String upString = subString.toUpperCase();
		return subjectDAO.getSubjectBySubString(upString);
	}

	public Subject findById(long id) {
		return this.subjectDAO.findById(id);
	}
	
	public Subject findByName(String name) {
		return this.subjectDAO.findBySubjectName(name);
	}
	
	public Subject create(Subject subject) {
		return this.subjectDAO.save(subject);
	}

	public List<Subject> createForList(Subject[] arrayDisciplinas) throws ServletException{
		for (int i = 0; i < arrayDisciplinas.length; i++){
			Subject sub = subjectDAO.findBySubjectName(arrayDisciplinas[i].getSubjectName());
			if (sub == null) {
				subjectDAO.save(arrayDisciplinas[i]);
			} else {

				String mensagem = "Erro ao cadastrar! Disciplina: " + arrayDisciplinas[i].getSubjectName() + " já cadastrada na base de dados.";
				throw new ServletException(mensagem);
			}

		}
		return this.subjectDAO.findAll();
	}
	
	public List<Subject> list() {
		return this.subjectDAO.findAll();
	}
	
	public void delete(long id) {
		this.subjectDAO.deleteById(id);
	}

}
