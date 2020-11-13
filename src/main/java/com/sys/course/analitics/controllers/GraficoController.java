package com.sys.course.analitics.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GraficoController {
	
//	@Autowired
//	private AulaService aulaService;

	
	@GetMapping("exibirGraficoBarra")
	public String barGraph(Model model) {
		
//		Iterable<Aula>aulas = aulaService.obterAulas();
//		model.addAttribute("aulas", aulas);
//		
	    Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Cliente ServidorJava", 60);
		surveyMap.put("Qualidade de Software", 25);
		surveyMap.put("Programação Mobile", 20);
		surveyMap.put("Topicos Especiais", 15);
		surveyMap.put("Análise II", 75);

		model.addAttribute("surveyMap", surveyMap);
     
		return "graficos/graficoBarra";
	}

	@GetMapping("exibirGraficoPizza")
	public String pieChart(Model model) {
		model.addAttribute("pass", 50);
		model.addAttribute("fail", 25);
		model.addAttribute("down", 15);
		model.addAttribute("up", 10);
		return "graficos/graficoPizza";
	}

}
