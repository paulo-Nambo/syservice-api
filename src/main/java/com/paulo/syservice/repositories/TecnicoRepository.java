package com.paulo.syservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paulo.syservice.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
 
	@Query("SELECT obj Tecnico obj WHERE obj.bi =:bi")
	Tecnico findByCPF(@Param("bi") String bi);

}
