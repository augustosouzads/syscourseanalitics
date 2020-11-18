package com.sys.course.analitics.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Aula;
import com.sys.course.analitics.repositories.AulaRepository;

@Service
public class AulaService {
	
	@Autowired
	private AulaRepository repository;

	public Iterable<Aula> obterAulas(){
		Iterable<Aula> aulas = repository.findAll();
		return aulas;
	}
	
	public void salvarAula (Aula aula) {
		repository.save(aula);
	}
	
	public void deletarAula(Long aulaId) {
		repository.deleteById(aulaId);;
	}
	
	public Aula atualizarAula (Long aulaId, Aula aula) {
		aula.setAulaId(aulaId);
		repository.save(aula);
		return new Aula();
	}
	
	public Optional<Aula> acharAulaPorId(Long aulaId) {
		Optional<Aula> aula = repository.findById(aulaId);
		return aula;
	}
	
	public List<Aula> relatorioDeAulaPorDisciplina(Long disciplina) {
		return repository.obterAulasPorDisciplina(disciplina);
	}
	
	public List<Aula> relatorioDeAulaPorDisciplinaTitulo(String disciplina){
		return repository.obterAulasPorDisciplinaTitulo(disciplina);
	}
	
	public List<Aula> relatorioDeAulaPorPeriodo(LocalDate dataInicial,LocalDate dataFinal){
		return repository.obterAulasPorPeriodo(dataInicial, dataFinal);
	}
}


