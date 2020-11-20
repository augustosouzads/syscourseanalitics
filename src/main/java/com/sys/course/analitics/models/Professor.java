package com.sys.course.analitics.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROFESSOR")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROFESSOR_id")
	private Long professorId;
	
	@Column(name = "NOME")
	@NotNull
	@NotEmpty(message = "Ops! esquecemos o Nome!")
	private String nome;
	
	@Column(name = "SOBRENOME")
	@NotNull
	@NotEmpty(message = "Ops! esquecemos o sobrenome")
	private String sobrenome;

	@Column(name = "EMAIL")
	@NotNull
	@NotEmpty(message = "Ops! esquecemos o email")
	private String email;

	@Column(name = "TELEFONE")
	@NotNull
	@NotEmpty(message = "Ops! esquecemos o telefone")
	private String telefone;
	
	public Professor(String nome, String sobrenome, String email, String telefone) {
 
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
	
	}
	
 
}
