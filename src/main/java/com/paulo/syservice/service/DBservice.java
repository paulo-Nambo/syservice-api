package com.paulo.syservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.syservice.domain.Cliente;
import com.paulo.syservice.domain.OrdemServ;
import com.paulo.syservice.domain.Tecnico;
import com.paulo.syservice.domain.enuns.Prioridade;
import com.paulo.syservice.domain.enuns.Status;
import com.paulo.syservice.repositories.ClienteRepository;
import com.paulo.syservice.repositories.OrdemServRepository;
import com.paulo.syservice.repositories.TecnicoRepository;

@Service
public class DBservice {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServRepository ordemservRepository;

	public void instaciaDB() {
		Tecnico t1 = new Tecnico(null, "Paulo Baltazar", "144.785.300-84", "(+258) 849442329");
		Tecnico t2 = new Tecnico(null, " Baltazar", "144.785.300-85", "(+258) 844442329");
		Cliente c1 = new Cliente(null, "Carlos Baltazar", "598.508.200-800", "(+258) 849442326");
		OrdemServ ord1 = new OrdemServ(null, Prioridade.ALTA, "Trocar o notebook", Status.ANDAMENTO, t1, c1);

		t1.getList().add(ord1);
		c1.getList().add(ord1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemservRepository.saveAll(Arrays.asList(ord1));

	}

}
