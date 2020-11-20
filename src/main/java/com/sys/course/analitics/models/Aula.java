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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

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
		@NotNull
		@NotEmpty(message = "Favor preencher o campo")
		@Min(value = 0, message = "Quantidade invalida")
		@Max(value = 80, message = "Quantidade invalida")
		private int quantidadeAlunos;
		
		@Column(name = "DATA")
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		@NotNull
		@NotEmpty(message = "Campo data pode estar vazio")
		private LocalDate data;
		
		@Column(name = "LINK")
		@NotNull
		@NotEmpty(message = "Campo link pode estar vazio")
		private String link;

		@Column(name = "CONTEUDO")
		@NotNull
		@NotEmpty(message = "Campo conteudo pode estar vazio")
		private String conteudo;
				
		@ManyToOne
		@JoinColumn(name = "DISCIPLINA_id")
		private Disciplina disciplinaId;
		
		@ManyToOne
		@JoinColumn(name = "PROFESSOR_id")
		private Professor professorId;
		
		public Aula(int quantidadeAlunos, LocalDate data, String link, String conteudo, Disciplina disciplinaId,
				 Professor professorId) {

			this.quantidadeAlunos = quantidadeAlunos;
			this.data =data;
			this.link = link;
			this.conteudo = conteudo;
			this.disciplinaId = disciplinaId;
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
