package com.sys.course.analitics.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sys.course.analitics.models.Aula;
import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.services.AulaService;
import com.sys.course.analitics.services.DisciplinaService;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AulaService service;
	
	public ReportController(DisciplinaService disciplinaService, AulaService service) {
		this.disciplinaService = disciplinaService;
		this.service = service;
		
	}
	
	@GetMapping("/html")
	public String html(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		if (disciplina == null) {
			return "relatorios/relatorio";
		}
		model.addAttribute("list", service.relatorioDeAulaPorDisciplina(disciplina));
		return "relatorios/relatorio";
	}
	
	@GetMapping("/graficoDeBarras")
	public String graficoDeBarras(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		if (disciplina == null) {
			return "relatorios/graficoDeBarras";
		}
		
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		model.addAttribute("Data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("Conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("Matriculados", list.stream().map(Aula::getDisciplinaId).collect(Collectors.toList()));		
		return "relatorios/graficoDeBarras";
	}
	
	@GetMapping("/graficoDeBarras2")
	public String graficoDeBarras2(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		if (disciplina == null) {
			return "graficos/graficoBarra";
		}
		
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		model.addAttribute("Data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("Conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("Matriculados", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getQuantidadeAluno).collect(Collectors.toList()));
		return "graficos/graficoBarra";
	}
	

}
