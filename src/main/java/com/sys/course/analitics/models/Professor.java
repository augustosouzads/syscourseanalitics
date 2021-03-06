package com.sys.course.analitics.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROFESSOR")
@Data
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROFESSOR_id")
	private Long professorId;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "SOBRENOME")
	private String sobrenome;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "TELEFONE")
	private String telefone;
	
	@OneToMany(mappedBy = "professorId")
	private List<Aula>aulas;
	
    public Professor() {		
	}
	
	 public Professor(String nome, String sobrenome, String email, String telefone){
	        this.nome = nome;
	        this.sobrenome = sobrenome;
	        this.email = email;
	        this.telefone = telefone;
	    }
	
}
