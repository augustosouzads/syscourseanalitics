package com.sys.course.analitics.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sys.course.analitics.models.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Long>{
	
//	@Query(value = "SELECT TA.data, TA.qtde_alunos , TD.qtde_aluno,\n" + 
//			"TT.turma_nome,TC.sigla,  TD.titulo\n" + 
//			"FROM aula TA \n" + 
//			"INNER JOIN disciplina TD ON TA.disciplina_id = TD.disciplina_id\n" + 
//			"INNER JOIN turma TT ON TA.turma_id = TT.turma_id\n" + 
//			"INNER JOIN curso TC ON TT.curso_id = TC.curso_id\n" + 
//			"WHERE TD.titulo = :disciplina", nativeQuery = true )
//	public List<Aula> obterAulasPorDisciplina(@Param("disciplina")String disciplina);
//	
	
	@Query(value = "select aula.* from aula as aula where aula.disciplina_id = disciplina_id and disciplina_id =:disciplina", nativeQuery = true )
	public List<Aula> obterAulasPorDisciplina(@Param("disciplina")Long disciplina);
	
	 

	
	/*
	 * Documentação da CrudRepository para mais informações sobre os metodos
	 * Segue Link abaixo:
	 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/
	 * html/repositories.html
	 */
}
