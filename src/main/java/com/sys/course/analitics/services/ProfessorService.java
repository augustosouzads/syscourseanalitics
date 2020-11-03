package com.sys.course.analitics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Professor;
import com.sys.course.analitics.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository repository;

	public Iterable<Professor> obterProfessores(){
		Iterable<Professor> professores = repository.findAll();
		return professores;
	}
	
	public void salvarProfessor (Professor professor) {
		repository.save(professor);
	}
	
	public void deletarProfessor(Long id) {
		repository.deleteById(id);;
	}
	
	public Professor atualizarProfessor (Long professorId, Professor professor) {
		professor.setProfessorId(professorId);
		repository.save(professor);
		return new Professor();
	}
	
	public Optional<Professor> acharProfessorPorId(Long professorId) {
		Optional<Professor> professor = repository.findById(professorId);
		return professor;
	}
}


