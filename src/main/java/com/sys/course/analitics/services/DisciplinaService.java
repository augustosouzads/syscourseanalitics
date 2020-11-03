package com.sys.course.analitics.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository repository;

	public List<Disciplina> obterDisciplinas(){
		List<Disciplina> disciplinas = repository.findAll();
		return disciplinas;
	}
	
	public void salvarDisciplina (Disciplina disciplina) {
		repository.saveAndFlush(disciplina);
	}
	
	public void deletarDisciplina(Long disciplinaId) {
		repository.deleteById(disciplinaId);
	}
	
	public Disciplina atualizarDisciplina (Long disciplinaId, Disciplina disciplina) {
		disciplina.setDisciplinaId(disciplinaId);
		repository.saveAndFlush(disciplina);
		return new Disciplina();
	}
	
	public Optional<Disciplina> acharProfessorPorId(Long disciplinaId) {
		Optional<Disciplina> disciplina = repository.findById(disciplinaId);
		return disciplina;
	}
}


