package com.sys.course.analitics.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
	public ModelAndView cadastrarAula(@Valid Aula aula,BindingResult bindingResult ){
			
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("aulas/aulaErro");
			Iterable<Aula> aulas = service.obterAulas(); 
			//modelAndView.addObject("aulas",aulas);
			//modelAndView.addObject("aulaObj",aula);
			
			List<String> msg =  new ArrayList<String>();
			for(ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());
			}
		    
			modelAndView.addObject("msg",msg);
			return modelAndView;
		}			
			service.salvarAula(aula);	
			ModelAndView modelAndView = new ModelAndView("aulas/listaAulas");
			Iterable<Aula> aulas = service.obterAulas(); 
			modelAndView.addObject("aulas",aulas);
			
			return modelAndView;
		
		
		
//		    Aula novaAula = new Aula(quantidadeAlunos, data,link, conteudo, disciplinaId,
//		   professorId);
//		    
//		    service.salvarAula(novaAula);
//		   
//     	    Iterable<Aula> aulas = service.obterAulas(); 		    
//		    model.addAttribute("aulas", aulas); 
		
	}
	
	@RequestMapping(value = "deletarAula", method = RequestMethod.GET)
	public String deletarAula(@RequestParam("aulaId") Long aulaId, Model model) {
		
		service.deletarAula(aulaId);
		
 	    Iterable<Aula> aulas = service.obterAulas(); 
	    
 	    model.addAttribute("aulas", aulas); 
		
		return "aulas/listaAulas";
	}
	
}
