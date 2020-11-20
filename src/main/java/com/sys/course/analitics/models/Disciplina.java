package com.sys.course.analitics.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DISCIPLINA")
@AllArgsConstructor
@NoArgsConstructor
@Data
//@ToString(exclude = "cursos")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISCIPLINA_id")
	@NotNull
	@NotEmpty(message = "Campo não pode estar vazio")
	private Long disciplinaId;

	@Column(name = "TITULO")
	@NotNull
	@NotEmpty(message = "Campo não pode estar vazio")
	private String titulo;

	@Column(name = "QTDE_ALUNO")
	@NotNull
	@NotEmpty(message = "Campo não pode estar vazio")
	@Min(value = 0, message = "Quantidade invalida")
	@Max(value = 80, message = "Quantidade invalida")
	private Integer quantidadeAluno;

	@Column(name = "DIA_SEMANA")
	@NotNull
	@NotEmpty(message = "Campo não pode estar vazio")
	private String diaDaSemana;

	@JoinColumn(name = "TURMA_id")	
	@ManyToOne
	@NotNull
	@NotEmpty(message = "Campo não pode estar vazio")
	private Turma turmaId;

	public Disciplina(String titulo, Integer quantidadeAluno, String diaDaSemana, Turma turmaId) {

		this.titulo = titulo;
		this.quantidadeAluno = quantidadeAluno;
		this.diaDaSemana = diaDaSemana;
		this.turmaId = turmaId;

	}

	@Override
	public String toString() {
		return "Disciplina [titulo=" + titulo + "," + " quantidadeAluno=" + quantidadeAluno + "," + " diaDaSemana="
				+ diaDaSemana + "," + "turma=" + turmaId+ "]";
	}

}
