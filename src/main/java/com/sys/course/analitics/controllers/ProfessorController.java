package com.sys.course.analitics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String cadastrarProfessor(@RequestParam("nome") String nome, @RequestParam("sobrenome") String sobrenome,@RequestParam("email") String email,
		    @RequestParam("telefone") String telefone, Model model ){

		    Professor novoProfessor = new Professor(nome, sobrenome, email, telefone);
		    
		    service.salvarProfessor(novoProfessor);
		   
     	    Iterable<Professor> professores = service.obterProfessores(); 
		    
		    model.addAttribute("professores", professores); 
		
			return "professores/listaProfessores";
	}
	
	@RequestMapping(value = "deletarProfessor", method = RequestMethod.GET)
	public String deletarProfessor(@RequestParam("professorId") Long professorId, Model model) {
		
		service.deletarProfessor(professorId);
		
		Iterable<Professor> professores = service.obterProfessores(); 
	    
	    model.addAttribute("professores", professores); 
	
		return "professores/listaProfessores";
		
	}
	
}
