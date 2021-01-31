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
		
		Iterable<Disciplina> disciplinas = disciplinaService.obterDisciplinas();
		model.addAttribute("disciplinas", disciplinas);
		
		return "cursos/cursoForm";
	}

	@RequestMapping(value = "cadastrarCurso", method = RequestMethod.POST)
	public ModelAndView cadastrarCurso(@Valid Curso curso,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cursos/cursoErro");
						
			List<String> msg =  new ArrayList<String>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
		    
			modelAndView.addObject("msg",msg);
			return modelAndView;
		}   
		service.salvarCurso(curso);	
		ModelAndView modelAndView = new ModelAndView("cursos/listaCursos");
		Iterable<Curso> cursos = service.obterCursos(); 
		modelAndView.addObject("cursos",cursos);
		
		return modelAndView;

	}
	
	@RequestMapping(value = "deletarCurso", method = RequestMethod.GET)
	public String deletarCurso(@RequestParam("cursoId") Long cursoId, Model model) {
		
		service.deletarCurso(cursoId);
		
		Iterable<Curso> cursos = service.obterCursos(); 
	    
	    model.addAttribute("cursos", cursos); 
	
		return "cursos/listaCursos";
		
	}
	
}
