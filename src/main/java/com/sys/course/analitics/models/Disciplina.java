package com.sys.course.analitics.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	@JoinColumn(name = "TURMA_id")
	@ManyToOne
	private Turma turma;

	public Disciplina(String titulo, Integer quantidadeAluno, String diaDaSemana, Turma turma) {

		this.titulo = titulo;
		this.quantidadeAluno = quantidadeAluno;
		this.diaDaSemana = diaDaSemana;
		this.turma = turma;

	}

	@Override
	public String toString() {
		return "Disciplina [titulo=" + titulo + "," + " quantidadeAluno=" + quantidadeAluno + "," + " diaDaSemana="
				+ diaDaSemana + "," + "turma=" + turma+ "]";
	}

}
