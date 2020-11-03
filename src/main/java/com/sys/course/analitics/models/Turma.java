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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "TURMA")
@Data	
@ToString(exclude = "aulas")
public class Turma {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "TURMA_id")
		private Long turmaId;
		
		@Column(name = "TURMA_NOME")
		private String turmaNome;
		
		@Column(name = "PERIODO")
		private String periodo;

		@OneToMany(mappedBy = "turmaId")
		private List<Aula>aulas;
		
		@ManyToOne
		@JoinColumn(name = "CURSO_id")
		private Curso curso;
		
		@ManyToMany
		@JoinTable(name = "TURMA_DISCIPLINA", joinColumns = @JoinColumn(name = "TURMA_id"),
				  inverseJoinColumns = @JoinColumn(name = "DISCIPLINA_id"))
		private List<Disciplina>disciplinas;

		public Turma(){
		}
		
		public Turma(String turmaNome, String periodo){
		        this.turmaNome = turmaNome;
		        this.periodo = periodo;
		}

}
