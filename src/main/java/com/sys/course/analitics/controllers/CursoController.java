package com.sys.course.analitics.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.services.CursoService;
import com.sys.course.analitics.services.DisciplinaService;

@Controller
public class CursoController {
	
	@Autowired
	DisciplinaService disciplinaService;

	@Autowired
	private CursoService service;
	
	@RequestMapping("listaCursos")
	public String listaCursos(Model model) {
		
		 Iterable<Curso> cursos = service.obterCursos();
		 model.addAttribute("cursos",cursos);
		 
		return "cursos/listaCursos";
	}
	
	@RequestMapping("cursoForm")
	public String cursosForm(Model model) {
		
		List<Disciplina> disciplinas = disciplinaService.obterDisciplinas();
		model.addAttribute("disciplinas", disciplinas);
		
		return "cursos/cursoForm";
	}

	@RequestMapping(value = "cadastrarCurso", method = RequestMethod.POST)
	public String cadastrarCurso(@RequestParam("cursoTitulo") String cursoTitulo,
			@RequestParam("sigla") String sigla,  Model model ){

		Curso novoCurso = new Curso(cursoTitulo, sigla);
		    
		    service.salvarCurso(novoCurso);
		   
     	    Iterable<Curso> cursos = service.obterCursos(); 
		    
		    model.addAttribute("cursos", cursos); 
		
			return "cursos/listaCursos";
	}
	
	@RequestMapping(value = "deletarCurso", method = RequestMethod.GET)
	public String deletarCurso(@RequestParam("cursoId") Long cursoId, Model model) {
		
		service.deletarCurso(cursoId);
		
		Iterable<Curso> cursos = service.obterCursos(); 
	    
	    model.addAttribute("cursos", cursos); 
	
		return "cursos/listaCursos";
		
	}
	
}
