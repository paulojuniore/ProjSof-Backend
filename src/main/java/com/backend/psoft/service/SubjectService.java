package com.backend.psoft.service;

import com.backend.psoft.comparators.SubjectsComparatorComments;
import com.backend.psoft.comparators.SubjectsComparatorLikes;
import com.backend.psoft.comparators.SubjectsComparatorProportionLikesAndDislikes;
import com.backend.psoft.comparators.SubjectsComparatorUnlikes;
import com.backend.psoft.dao.SubjectDAO;
import com.backend.psoft.exception.subjects.ExistingDisciplineException;
import com.backend.psoft.model.Like;
import com.backend.psoft.model.Subject;
import com.backend.psoft.model.SubjectProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Paulo Mendes da Silva Júnior - 117210922
 *
 */
@Service
public class SubjectService {

	@Autowired
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

	/*
	 * Método que adiciona várias disciplinas ao sistema a partir de uma
	 * lista de disciplinas recebida
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public List<Subject> createForList(Subject[] arrayDisciplinas) throws ExistingDisciplineException {
		for (int i = 0; i < arrayDisciplinas.length; i++){
			Subject sub = subjectDAO.findBySubjectName(arrayDisciplinas[i].getSubjectName());
			if (sub == null) {
				subjectDAO.save(arrayDisciplinas[i]);
			} else {
				String mensagem = "Erro ao cadastrar! Disciplina: " + arrayDisciplinas[i].getSubjectName() + " já cadastrada na base de dados.";
				throw new ExistingDisciplineException(mensagem);
			}
		}
		return this.subjectDAO.findAll();
	}

	/*
	 * Método que gera o perfil de uma disciplina para envia-lo ao front apenas
	 * com os dados necessários.
	 *
	 * Abel Antunes de Lima Neto - 117210287
	 */
	public SubjectProfile getPerfilSubject(long id, String emailUser) {
		SubjectProfile ret = new SubjectProfile();
		Subject subject = findById(id);
		ret.setComments(subject.getComments());
		ret.setId(id);
		ret.setLikes(subject.countNoLikes());
		ret.setUnlikes(subject.countNoUnlikes());
		ret.setName(subject.getSubjectName());
		Like likeUser = subject.getLike(emailUser);
		if(likeUser == null) {
			ret.setAvalUser(0);
		} else {
			ret.setAvalUser(likeUser.getLike_type());
		}
		return ret;
	}

	public List<Subject> list() {
		return this.subjectDAO.findAll();
	}
	
	public void delete(long id) {
		this.subjectDAO.deleteById(id);
	}

	/*
	 * Método que ordena as disciplinas a partir do numero de likes e retorna em uma lista
	 */
	public List<Subject> orderByNumLikes() {
		List<Subject> subjects = subjectDAO.findAll();
		Collections.sort(subjects, new SubjectsComparatorLikes());
		return subjects;
	}


	/*
	 * Método que ordena as disciplinas a partir do numero de deslikes e retorna em uma lista
	 */
	public List<Subject> orderByNumDislikes() {
		List<Subject> subjects = subjectDAO.findAll();
		Collections.sort(subjects, new SubjectsComparatorUnlikes());
		return subjects;
	}


	/*
	 * Método que ordena as disciplinas a partir do numero de comentários e retorna em uma lista
	 */
	public List<Subject> orderByNumOfComments() {
		List<Subject> subjects = subjectDAO.findAll();
		Collections.sort(subjects, new SubjectsComparatorComments());
		return subjects;
	}

	/*
	 * Método que ordena as disciplinas a partir da proporção likes/deslikes e retorna em uma lista
	 */
	public List<Subject> orderByProportionLikesDislikes() {
		List<Subject> subjects = subjectDAO.findAll();
		Collections.sort(subjects, new SubjectsComparatorProportionLikesAndDislikes());
		return subjects;
	}

}
