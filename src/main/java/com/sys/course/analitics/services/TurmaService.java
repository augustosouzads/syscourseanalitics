package com.sys.course.analitics.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Turma;
import com.sys.course.analitics.repositories.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	private TurmaRepository repository;

	public Iterable<Turma> obterTurmas(){
		Iterable<Turma> turmas = repository.findAll();
		return turmas;
	}
	
	public void salvarTurma (Turma turma) {
		repository.save(turma);
	}
	
	public void deletarTurma(Long turmaId) {
		repository.deleteById(turmaId);;
	}
	
	public Turma atualizarProfessor (Long turmaId, Turma turma) {
		turma.setTurmaId(turmaId);
		repository.save(turma);
		return new Turma();
	}
	
	public Optional<Turma> acharTurmaPorId(Long turmaId) {
		Optional<Turma> turma = repository.findById(turmaId);
		return turma;
	}
}


