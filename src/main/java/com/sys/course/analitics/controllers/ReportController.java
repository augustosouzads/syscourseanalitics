package com.sys.course.analitics.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sys.course.analitics.models.Aula;
import com.sys.course.analitics.models.Curso;
import com.sys.course.analitics.models.Disciplina;
import com.sys.course.analitics.models.Turma;
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
	
	@GetMapping("/relatorioPorDisciplina")
	public String relatorioPorDisciplina(Model model) {
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
			return "relatorios/relatorioPorDisciplina";
	}
	
	@GetMapping("/html")
	public String html() {
		return "relatorios/relatorioIndex";
	}
	
	@GetMapping("/graficoDeBarras")
	public String graficoDeBarras(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		if (disciplina == null) {
			return "relatorios/relatorio";
		}
		
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		model.addAttribute("Data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("Conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("Matriculados", list.stream().map(Aula::getDisciplinaId).collect(Collectors.toList()));		
		return "relatorios/graficoDeBarras";
	}
	
	@GetMapping("/graficoDeBarras2")
	public String graficoDeBarras2(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		model.addAttribute("disciplinaTitulo", disciplinaService.acharDisciplinaPorId(disciplina));
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		if (disciplina == null) {
			return "relatorios/relatorio";
		}
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		model.addAttribute("Data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("Conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("Matriculados", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getQuantidadeAluno).collect(Collectors.toList()));
		return "graficos/graficoBarra";
	}
	
	@GetMapping("/graficoDeBarrasPorDisciplinaTitulo")
	public String graficoDeBarrasPorDisciplinaTitulo(@RequestParam(required = false, value = "disciplina") String disciplina, Model model) {
		
		if (disciplina == null) {
			return "relatorios/relatorioIndex";
		}
		List<Aula> list = service.relatorioDeAulaPorDisciplinaTitulo(disciplina);
		model.addAttribute("aulas", list);
		model.addAttribute("data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("matriculados", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getQuantidadeAluno).collect(Collectors.toList()));
		model.addAttribute("curso", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getCursoId).map(Curso::getSigla).collect(Collectors.toList()));
		model.addAttribute("turma", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getTurmaNome).collect(Collectors.toList()));

		return "graficos/graficoBarraPorTituloDisciplina";
	}
	
	@GetMapping("/graficoDeBarrasPorPeriodo")
	public String graficoDeBarrasPorPeriodo(@RequestParam(required = false, value = "dataInicial") LocalDate dataInicial,
			@RequestParam(required = false, value = "dataFinal") LocalDate dataFinal,Model model) {
		
		if ((dataFinal == null) || (dataInicial == null)){
			return "relatorios/relatorio";
		}
		List<Aula> list = service.relatorioDeAulaPorPeriodo(dataInicial, dataFinal);
		model.addAttribute("aulas", list);
		model.addAttribute("data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("matriculados", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getQuantidadeAluno).collect(Collectors.toList()));
		model.addAttribute("curso", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getCursoId).map(Curso::getSigla).collect(Collectors.toList()));
		model.addAttribute("turma", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getTurmaNome).collect(Collectors.toList()));
		model.addAttribute("disciplina", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTitulo).collect(Collectors.toList()));
		return "graficos/graficoBarraPorPeriodo";
	}

}
