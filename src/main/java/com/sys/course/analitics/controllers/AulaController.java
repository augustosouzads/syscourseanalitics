package com.sys.course.analitics.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sys.course.analitics.models.Aula;
import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.models.Professor;
import com.sys.course.analitics.models.Turma;
import com.sys.course.analitics.services.AulaService;
import com.sys.course.analitics.services.CursoService;
import com.sys.course.analitics.services.DisciplinaService;
import com.sys.course.analitics.services.ProfessorService;
import com.sys.course.analitics.services.TurmaService;


@Controller
public class AulaController {

	@Autowired
	private AulaService service;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired	
	private CursoService cursoService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	TurmaService turmaService;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) { 
		binder.getBindingResult();
	}

	
	@RequestMapping("listaAulas")
	public String listaAulas(Model model) {
		
		 Iterable<Aula> aulas = service.obterAulas();//capturando os objetos do banco chamando a camada de serviço usando o ConvidadoService
		 model.addAttribute("aulas",aulas);//deixando disponivel para a pagina atraves do Model
		 
		return "aulas/listaAulas";
	}
	
	@RequestMapping("aulaForm")
	public String aulaForm(Model model) {
			
	Iterable<Disciplina> disciplinas = disciplinaService.obterDisciplinas();
	model.addAttribute("disciplinas",disciplinas);
	
	Iterable<Curso> cursos = cursoService.obterCursos();
	model.addAttribute("cursos",cursos);
	
	Iterable<Professor> professores = professorService.obterProfessores(); 
	model.addAttribute("professores", professores);
	
	Iterable<Turma> turmas = turmaService.obterTurmas(); 
    model.addAttribute("turmas", turmas);
		
		return "aulas/aulaForm";
	}
	

	@RequestMapping(value = "cadastrarAula", method = RequestMethod.POST)
	public String cadastrarAula(@RequestParam("quantidadeAlunos")int quantidadeAlunos,@RequestParam("data")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate data,@RequestParam("link") String link,@RequestParam("conteudo") String conteudo,
	@RequestParam("disciplinaId")Disciplina disciplinaId,@RequestParam("turmaId")Turma turmaId,
	@RequestParam("professorId")Professor professorId, Model model ){
			
		    Aula novaAula = new Aula(quantidadeAlunos, data,link, conteudo, disciplinaId,
		  	turmaId, professorId);
		    
		    service.salvarAula(novaAula);
		   
     	    Iterable<Aula> aulas = service.obterAulas(); 		    
		    model.addAttribute("aulas", aulas); 
		    
		    
		
			return "aulas/listaAulas";
	}
	
	@RequestMapping(value = "deletarAula", method = RequestMethod.GET)
	public String deletarAula(@RequestParam("aulaId") Long aulaId, Model model) {
		
		service.deletarAula(aulaId);
		
 	    Iterable<Aula> aulas = service.obterAulas(); 
	    
 	    model.addAttribute("aulas", aulas); 
		
		return "aulas/listaAulas";
	}
	
}
