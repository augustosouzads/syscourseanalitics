package com.sys.course.analitics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository repository;

	public Iterable<Disciplina> obterDisciplinas(){
		Iterable<Disciplina> disciplinas = repository.findAll();
		return disciplinas;
	}
	
	public void salvarDisciplina (Disciplina disciplina) {
		repository.save(disciplina);
	}
	
	public void deletarDisciplina(Long disciplinaId) {
		repository.deleteById(disciplinaId);
	}
	
	public Disciplina atualizarDisciplina (Long disciplinaId, Disciplina disciplina) {
		disciplina.setDisciplinaId(disciplinaId);
		repository.save(disciplina);
		return new Disciplina();
	}
	
	public Optional<Disciplina> acharDisciplinaPorId(Long disciplinaId) {
		Optional<Disciplina> disciplina = repository.findById(disciplinaId);
		return disciplina;
	}
}


