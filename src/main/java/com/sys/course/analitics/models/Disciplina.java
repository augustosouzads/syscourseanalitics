package com.sys.course.analitics.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "DISCIPLINA")
@Data
@ToString(exclude = "aulas")
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
	
	@Column(name = "TURNO")
	private String turno;
	
	@OneToMany(mappedBy = "disciplinaId")
	private List<Aula>aulas;

	@ManyToMany(mappedBy = "disciplinas")
	private List<Curso>cursos;
	
	@ManyToMany(mappedBy = "disciplinas")
	private List<Turma>turmas;
	
    public Disciplina() {		
	}
	
	 public Disciplina(String titulo, Integer quantidadeAluno , String diaDaSemana,
			String turno,List<Turma>turmas, List<Aula> aulas, List<Curso> cursos){
	        
		    this.titulo	= titulo;
	        this.quantidadeAluno = quantidadeAluno;
	        this.diaDaSemana = diaDaSemana;
	        this.turno = turno;
	        this.aulas = aulas;
	        this.cursos = cursos;
	        this.turmas = turmas;
	   
	    }
	 
//		@Override
//		public String toString() {
//			return "Disciplina [titulo=" + titulo + ", quantidadeAluno=" + quantidadeAluno + ", diaDaSemana=" + diaDaSemana + ","
//					+ "aulas="+ aulas+",cursos=" + cursos + ",turmas=" + turmas + "]";
//		}

}
