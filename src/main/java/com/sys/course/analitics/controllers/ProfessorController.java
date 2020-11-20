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

import com.sys.course.analitics.models.Professor;
import com.sys.course.analitics.services.ProfessorService;

@Controller
public class ProfessorController {

	@Autowired
	private ProfessorService service;
	
	@RequestMapping("listaProfessores")
	public String listaProfessores(Model model) {
		
		 Iterable<Professor> professores = service.obterProfessores();//capturando os objetos do banco chamando a camada de serviço usando o ConvidadoService
		 model.addAttribute("professores",professores);//deixando disponivel para a pagina atraves do Model
		 
		return "professores/listaProfessores";
	}
	
	@RequestMapping("professorForm")
	public String professorForm() {
		
		return "professores/professorForm";
	}

	@RequestMapping(value = "cadastrarProfessor", method = RequestMethod.POST)
	public ModelAndView cadastrarProfessor(@Valid Professor professor,BindingResult bindingResult){
		

		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("professores/professorForm");
			Iterable<Professor> professores = service.obterProfessores(); 
			modelAndView.addObject("professores",professores);
			modelAndView.addObject("professorObj",professor);
			
			List<String> msg =  new ArrayList<String>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
		    
			modelAndView.addObject("msg",msg);
			return modelAndView;
		}			
			service.salvarProfessor(professor);	
			ModelAndView modelAndView = new ModelAndView("professores/listaProfessores");
			Iterable<Professor> professores = service.obterProfessores(); 
			modelAndView.addObject("professores",professores);
			
			return modelAndView;
	}
	
	@RequestMapping(value = "deletarProfessor", method = RequestMethod.GET)
	public String deletarProfessor(@RequestParam("professorId") Long professorId, Model model) {
		
		service.deletarProfessor(professorId);
		
		Iterable<Professor> professores = service.obterProfessores(); 
	    
	    model.addAttribute("professores", professores); 
	
		return "professores/listaProfessores";
		
	}
	
}
