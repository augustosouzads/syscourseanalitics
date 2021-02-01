package com.sys.course.analitics.controllers;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.sys.course.analitics.utils.PDFUtils;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AulaService service;

	
	@GetMapping("/relatorioPorDisciplina")
	public String relatorioPorDisciplina(Model model) {
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		return "relatorios/relatorioPorDisciplina";
	}
	
	@GetMapping("/relatorioPorPeriodo")
	public String relatorioPorPeriodo(Model model) {
		return "relatorios/relatorioPorPeriodo";
	}
	
	@GetMapping("/relatorioPorTitulo")
	public String relatorioPorTitulo(Model model) {
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinasPorTitulo());
		return "relatorios/relatorioPorTitulo";
	}
	
	@GetMapping("/html")
	public String html() {
		return "relatorios/relatorioIndex";
	}
	
	@GetMapping("/relatorioPdfPorPeriodo")
	public String relatorioPdfPorPeriodo() {
		return "relatorios/relatorioPdfPorPeriodo";		
	}
	
	@GetMapping("/gerarRelatorioPdfPorDisciplina")
	public String gerarRelatorioPdfPorDisciplina(Model model) {
		model.addAttribute("disciplinas", disciplinaService.obterDisciplinas());
		return "relatorios/relatorioPdfPorDisciplina";		
	}
	
	@GetMapping("/gerarRelatorioPdfPorPeriodo")
	public String gerarRelatorioPdfPorPeriodo() {
		return "relatorios/relatorioPdfPorPeriodo";		
	}
	
	@GetMapping("/graficoDeBarrasDisciplinaId")
	public String graficoDeBarrasDisciplinaId(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model) {
		
		if (disciplina == null) {
			return "relatorios/relatorioIndex";
		}
		
		model.addAttribute("disciplina",disciplina);
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		model.addAttribute("aulas", list);
		model.addAttribute("data", list.stream().map(Aula::getData).collect(Collectors.toList()));
		model.addAttribute("conectados", list.stream().map(Aula::getQuantidadeAlunos).collect(Collectors.toList()));
		model.addAttribute("matriculados", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getQuantidadeAluno).collect(Collectors.toList()));
		model.addAttribute("curso", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getCursoId).map(Curso::getSigla).collect(Collectors.toList()));
		model.addAttribute("turma", list.stream().map(Aula::getDisciplinaId).map(Disciplina::getTurmaId).map(Turma::getTurmaNome).collect(Collectors.toList()));
		return "relatorios/graficoDeBarras";
	}
	
	@GetMapping("/graficoDeBarrasPorDisciplinaTitulo")
	public String graficoDeBarrasPorDisciplinaTitulo(@RequestParam(required = false, value = "disciplina") String disciplina, Model model) {
		
		model.addAttribute("disciplina", disciplina);

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
	public String graficoDeBarrasPorPeriodo(@RequestParam("dataInicial")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataInicial,@RequestParam("dataFinal")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataFinal,Model model) {
		
		model.addAttribute("dataInicial", dataInicial);
		model.addAttribute("dataFinal", dataFinal);

		if ((dataFinal == null) || (dataInicial == null)){
			return "relatorios/relatorioIndex";
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
	
	@GetMapping("/pdfPorTituloDisciplina")
	public ResponseEntity<InputStreamResource> pdfPorTituloDisciplina(@RequestParam(required = false, value = "disciplina") String disciplina, Model model){
		
		if (disciplina == null) {
			return null;
		}
		List<Aula> list = service.relatorioDeAulaPorDisciplinaTitulo(disciplina);
		ByteArrayInputStream pdf = PDFUtils.gerarPdf(list);
		System.out.println("preenchi reelatorio");
	
	return ResponseEntity.ok().header("Content-Disposition", "inline; filename=report.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));	
		
	}
	
	@GetMapping("/pdfPorTituloPeriodo")
	public ResponseEntity<InputStreamResource> pdfPorTituloPeriodo(@RequestParam("dataInicial")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataInicial,@RequestParam("dataFinal")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dataFinal,Model model){
		
		if ((dataFinal == null) || (dataInicial == null)){
			return null;
		}
		List<Aula> list = service.relatorioDeAulaPorPeriodo(dataInicial, dataFinal);
		ByteArrayInputStream pdf = PDFUtils.gerarPdf(list);
		System.out.println("preenchi reelatorio");
	
	return ResponseEntity.ok().header("Content-Disposition", "inline; filename=report.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));	
		
	}
	
	@GetMapping("/pdfPorDisciplinaId")
	public ResponseEntity<InputStreamResource> pdfPorDisciplinaId(@RequestParam(required = false, value = "disciplina") Long disciplina, Model model){
		
		if (disciplina == null) {
			return null;
		}
		List<Aula> list = service.relatorioDeAulaPorDisciplina(disciplina);
		ByteArrayInputStream pdf = PDFUtils.gerarPdf(list);
		System.out.println("preenchi reelatorio");
	
	return ResponseEntity.ok().header("Content-Disposition", "inline; filename=report.pdf").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));	
		
	}

}
