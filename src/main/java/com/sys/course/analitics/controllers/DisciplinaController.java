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
import com.sys.course.analitics.models.Turma;
import com.sys.course.analitics.services.CursoService;
import com.sys.course.analitics.services.DisciplinaService;
import com.sys.course.analitics.services.TurmaService;

@Controller
public class DisciplinaController {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private TurmaService turmaService;

	@Autowired
	private DisciplinaService service;
	
	@RequestMapping("listaDisciplinas")
	public String listaDisciplinas(Model model) {
		
		 List<Disciplina> disciplinas = service.obterDisciplinas();
		 model.addAttribute("disciplinas",disciplinas);
		 
		return "disciplinas/listaDisciplinas";
	}
	
	@RequestMapping("disciplinaForm")
	public String disciplinaForm(Model model) {
		
		Iterable<Curso> cursos = cursoService.obterCursos();
		model.addAttribute("cursos",cursos);
		
		Iterable<Turma> turmas = turmaService.obterTurmas(); 
	    model.addAttribute("turmas", turmas);
		
		return "disciplinas/disciplinaForm";
	}
	

	@RequestMapping(value = "cadastrarDisciplina", method = RequestMethod.POST)
	public String cadastrarDisciplina(@RequestParam("titulo") String titulo, @RequestParam("quantidadeAluno")
	Integer quantidadeAluno,@RequestParam("diaDaSemana") String diaDaSemana,
	@RequestParam("cursos") List<Curso> cursos,
	@RequestParam("turmas") List<Turma> turmas, Model model ){

		Disciplina novaDisciplina = new Disciplina(titulo,quantidadeAluno,diaDaSemana,turmas,cursos);
		
	    service.salvarDisciplina(novaDisciplina);
	   
		List<Disciplina> disciplinas = service.obterDisciplinas();
		    
	    model.addAttribute("disciplinas", disciplinas); 
		
		return "disciplinas/listaDisciplinas";
	}
	
	@RequestMapping(value = "deletarDisciplina", method = RequestMethod.GET)
	public String deletarDisciplina(@RequestParam("disciplinaId") Long disciplinaId, Model model) {
		 
		service.deletarDisciplina(disciplinaId);
		
		List<Disciplina> disciplinas = service.obterDisciplinas();
	    
	    model.addAttribute("disciplinas", disciplinas); 

		return "disciplinas/listaDisciplinas";
		
	}
	
}
