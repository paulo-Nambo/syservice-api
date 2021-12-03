package com.paulo.syservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.paulo.syservice.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	@Query("SELECT obj Pessoa obj WHERE obj.bi =:bi")

	Pessoa findByCPF(@Param("bi") String bi);

}
