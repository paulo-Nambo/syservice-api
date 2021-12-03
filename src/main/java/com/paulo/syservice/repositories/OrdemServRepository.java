package com.paulo.syservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paulo.syservice.domain.OrdemServ;


@Repository
public interface OrdemServRepository extends JpaRepository<OrdemServ, Integer> {

}
