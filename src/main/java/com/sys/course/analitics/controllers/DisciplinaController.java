package com.sys.course.analitics.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.models.Professor;
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
		
		 Iterable<Disciplina> disciplinas = service.obterDisciplinas();
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
	public ModelAndView cadastrarDisciplina(@Valid Disciplina disciplina,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("disciplinas/disciplinaErro");
			Iterable<Disciplina> disciplinas = service.obterDisciplinas(); 
			modelAndView.addObject("disciplinas",disciplinas);
			modelAndView.addObject("disciplinaObj",disciplina);
			
			List<String> msg =  new ArrayList<String>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
		    
			modelAndView.addObject("msg",msg);
			return modelAndView;
		}			
			service.salvarDisciplina(disciplina);	
			ModelAndView modelAndView = new ModelAndView("disciplinas/listaDisciplinas");
			Iterable<Disciplina> disciplinas = service.obterDisciplinas(); 
			modelAndView.addObject("disciplinas", disciplinas);
			
			return modelAndView;

//		Disciplina novaDisciplina = new Disciplina(titulo,quantidadeAluno,diaDaSemana,turma);
//		
//	    service.salvarDisciplina(novaDisciplina);
//	   
//	    Iterable<Disciplina> disciplinas = service.obterDisciplinas();
//		    
//	    model.addAttribute("disciplinas", disciplinas); 
//		
//		return "disciplinas/listaDisciplinas";
	}
	
	@RequestMapping(value = "deletarDisciplina", method = RequestMethod.GET)
	public String deletarDisciplina(@RequestParam("disciplinaId") Long disciplinaId, Model model) {
		 
		service.deletarDisciplina(disciplinaId);
		
		Iterable<Disciplina> disciplinas = service.obterDisciplinas();
	    
	    model.addAttribute("disciplinas", disciplinas); 

		return "disciplinas/listaDisciplinas";
		
	}
	
}
