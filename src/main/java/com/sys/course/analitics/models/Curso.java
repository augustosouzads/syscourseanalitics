package com.sys.course.analitics.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CURSO")
@Data
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CURSO_id")
	private Long cursoId;
	
	@Column(name = "CURSO_TITULO")
	private String cursoTitulo;
	
	@Column(name = "SIGLA")
	private String sigla;
	
	@ManyToMany
	@JoinTable(name = "CURSO_DISCIPLINA", joinColumns = @JoinColumn(name = "CURSO_id"),
			  inverseJoinColumns = @JoinColumn(name = "DISCIPLINA_id"))
	private List<Disciplina>disciplinas;
	
	@OneToMany(mappedBy = "curso")
	private List<Turma>turmas;

	public Curso() {		
	}
	
	 public Curso(String cursoTitulo, String sigla, List<Disciplina> disciplinas){
	        this.cursoTitulo = cursoTitulo;
	        this.sigla = sigla;
	}
	
}
