package com.sys.course.analitics.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraficoController {

	@GetMapping("exibirGraficoBarra")
	public String barGraph(Model model) {
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Java", 40);
		surveyMap.put("Dev oops", 25);
		surveyMap.put("Python", 20);
		surveyMap.put(".Net", 15);
		surveyMap.put("kotlin", 75);

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
