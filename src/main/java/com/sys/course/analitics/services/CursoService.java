package com.sys.course.analitics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.repositories.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repository;

	public Iterable<Curso> obterCursos(){
		Iterable<Curso> cursos = repository.findAll();
		return cursos;
	}
	
	public void salvarCurso (Curso curso) {
		repository.save(curso);
	}
	
	public void deletarCurso(Long cursoId) {
		repository.deleteById(cursoId);;
	}
	
	public Curso atualizarProfessor (Long cursoId, Curso curso) {
		curso.setCursoId(cursoId);
		repository.save(curso);
		return new Curso();
	}
	
	public Optional<Curso> acharCursoPorId(Long cursoId) {
		Optional<Curso> curso = repository.findById(cursoId);
		return curso;
	}
}


