package com.sys.course.analitics.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "AULA")

@AllArgsConstructor
@NoArgsConstructor
@Data	
@ToString(exclude = "disciplinaId")
public class Aula {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "AULA_id")
		private Long aulaId;
		
		@Column(name = "QTDE_ALUNOS")
		private int quantidadeAlunos;
		
		@Column(name = "DATA")
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		private LocalDate data;
		
		@Column(name = "LINK")
		private String link;

		@Column(name = "CONTEUDO")
		private String conteudo;
				
		@ManyToOne
		@JoinColumn(name = "DISCIPLINA_id")
		private Disciplina disciplinaId;
		
		@ManyToOne
		@JoinColumn(name = "TURMA_id")
		private Turma turmaId;
		
		@ManyToOne
		@JoinColumn(name = "PROFESSOR_id")
		private Professor professorId;
		
		public Aula(int quantidadeAlunos, LocalDate data, String link, String conteudo, Disciplina disciplinaId,
				Turma turmaId, Professor professorId) {

			this.quantidadeAlunos = quantidadeAlunos;
			this.data =data;
			this.link = link;
			this.conteudo = conteudo;
			this.disciplinaId = disciplinaId;
			this.turmaId = turmaId;
			this.professorId = professorId;
		
		}


//		@Override
//		public String toString() {
//			return "Aula [quantidadeAlunos=" + quantidadeAlunos + ", data=" + data + ", link=" + link + ","
//					+ "conteudo="+ conteudo +",disciplinaId=" + disciplinaId + ","
//					+ "turmaId=" + turmaId + ",professorID=" + professorId +"]";
//		
//	}
	
}
