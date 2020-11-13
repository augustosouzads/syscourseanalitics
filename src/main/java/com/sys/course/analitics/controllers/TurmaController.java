package com.sys.course.analitics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.models.Turma;
import com.sys.course.analitics.services.CursoService;
import com.sys.course.analitics.services.TurmaService;

@Controller
public class TurmaController {
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private TurmaService service;
	
	@RequestMapping("listaTurmas")
	public String listaTurmas(Model model) {
		
		 Iterable<Turma> turmas = service.obterTurmas();
		 model.addAttribute("turmas",turmas);
		 
		return "turmas/listaTurmas";
	}
	
	@RequestMapping("turmaForm")
	public String turmaForm(Model model) {
		
		Iterable<Curso> cursos = cursoService.obterCursos();
		model.addAttribute("cursos",cursos);;
		
		return "turmas/turmaForm";
	}

	@RequestMapping(value = "cadastrarTurma", method = RequestMethod.POST)
	public String cadastrarTurma(@RequestParam("turmaNome") String turmaNome,
			@RequestParam("periodo") String periodo,
			@RequestParam("cursoId") Curso cursoId, Model model ){

		    Turma novaTurmma = new Turma(turmaNome,periodo,cursoId);
		    
		    service.salvarTurma(novaTurmma);
		   
     	    Iterable<Turma> turmas = service.obterTurmas(); 
		    
		    model.addAttribute("turmas", turmas); 
		
			return "turmas/listaTurmas";
	}
	
	@RequestMapping(value = "deletarTurma", method = RequestMethod.GET)
	public String deletarTurma(@RequestParam("turmaId") Long turmaId, Model model) {
		
		service.deletarTurma(turmaId);
		
 	    Iterable<Turma> turmas = service.obterTurmas(); 
	    
 	    model.addAttribute("turmas", turmas); 
		
		return "turmas/listaTurmas";
	}
	
}
