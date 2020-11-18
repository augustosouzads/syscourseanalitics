package com.sys.course.analitics.models;

import java.time.LocalDate;

public class RelatorioGrafico {
	
	private Long id;
	private LocalDate data;
	private Integer conectados;
	private Integer matriculados;
	private String turma;
	private String curso;
	private String titulo;
	
	public RelatorioGrafico() {
		
	}
		
	public RelatorioGrafico(Long id, LocalDate data, Integer conectados, Integer matriculados, String turma,
			String curso, String titulo) {
		this.id = id;
		this.data = data;
		this.conectados = conectados;
		this.matriculados = matriculados;
		this.turma = turma;
		this.curso = curso;
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Integer getConectados() {
		return conectados;
	}
	public void setConectados(Integer conectados) {
		this.conectados = conectados;
	}
	public Integer getMatriculados() {
		return matriculados;
	}
	public void setMatriculados(Integer matriculados) {
		this.matriculados = matriculados;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

}
