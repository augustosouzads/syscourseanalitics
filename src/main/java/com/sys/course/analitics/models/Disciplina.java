package com.sys.course.analitics.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private Long disciplinaId;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "QTDE_ALUNO")
	private Integer quantidadeAluno;

	@Column(name = "DIA_SEMANA")
	private String diaDaSemana;

	@ManyToMany
	private List<Curso> cursos;

	@ManyToMany
	private List<Turma> turmas;

	public Disciplina(String titulo, Integer quantidadeAluno, String diaDaSemana, List<Turma> turmas,
			List<Curso> cursos) {

		this.titulo = titulo;
		this.quantidadeAluno = quantidadeAluno;
		this.diaDaSemana = diaDaSemana;
		this.turmas = turmas;
		this.cursos = cursos;

	}

	@Override
	public String toString() {
		return "Disciplina [titulo=" + titulo + "," + " quantidadeAluno=" + quantidadeAluno + "," + " diaDaSemana="
				+ diaDaSemana + "," + "cursoId=" + cursos + "," + "turmaId=" + turmas + "]";
	}

}
