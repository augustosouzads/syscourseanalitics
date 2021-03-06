package com.sys.course.analitics.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sys.course.analitics.models.Turma;

@Repository
public interface TurmaRepository extends CrudRepository<Turma, Long>{
	
	/*
	 * Documentação da CrudRepository para mais informações sobre os metodos
	 * Segue Link abaixo:
	 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/
	 * html/repositories.html
	 */
}
