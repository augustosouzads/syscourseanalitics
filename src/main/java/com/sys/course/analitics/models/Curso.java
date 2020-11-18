package com.sys.course.analitics.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURSO")
@AllArgsConstructor
@NoArgsConstructor
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

	public Curso(String cursoTitulo, String sigla) {
		this.cursoTitulo = cursoTitulo;
		this.sigla = sigla;
	}

}
