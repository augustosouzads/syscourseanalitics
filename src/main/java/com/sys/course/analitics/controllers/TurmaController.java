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
import com.sys.course.analitics.models.Professor;
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
	public ModelAndView cadastrarTurma(@Valid Turma turma,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("turmas/turmaErro");
					
			List<String> msg =  new ArrayList<String>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
		    
			modelAndView.addObject("msg",msg);
			return modelAndView;
		}			
			service.salvarTurma(turma);	
			ModelAndView modelAndView = new ModelAndView("turmas/listaTurmas");
			Iterable<Turma>turmas = service.obterTurmas(); 
			modelAndView.addObject("turmas",turmas);
			
			return modelAndView;
	}
	
	@RequestMapping(value = "deletarTurma", method = RequestMethod.GET)
	public String deletarTurma(@RequestParam("turmaId") Long turmaId, Model model) {
				
			service.deletarTurma(turmaId);	
			
			Iterable<Turma> turmas = service.obterTurmas(); 	    
			
			model.addAttribute("turmas", turmas); 			
			
		
		return "turmas/listaTurmas";
	}
	
}
