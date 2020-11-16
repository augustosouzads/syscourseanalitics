package com.sys.course.analitics.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TURMA")
@AllArgsConstructor
@NoArgsConstructor
@Data	
public class Turma {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "TURMA_id")
		private Long turmaId;
		
		@Column(name = "TURMA_NOME")
		private String turmaNome;
		
		@Column(name = "PERIODO")
		private String periodo;

		@ManyToOne
		@JoinColumn(name = "CURSO_id")
		private Curso cursoId;
		
		public Turma(String turmaNome, String periodo, Curso cursoId) {
			
			this.turmaNome = turmaNome;
			this.periodo =periodo;
			this.cursoId = cursoId;
		}

	


}
