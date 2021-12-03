package com.paulo.syservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulo.syservice.domain.Cliente;
import com.paulo.syservice.domain.OrdemServ;
import com.paulo.syservice.domain.Tecnico;
import com.paulo.syservice.domain.enuns.Prioridade;
import com.paulo.syservice.domain.enuns.Status;
import com.paulo.syservice.dtos.OrdemservDTO;
import com.paulo.syservice.repositories.OrdemServRepository;
import com.paulo.syservice.service.exption.ObjectNotFaundExption;

@Service
public class OrdemservService {

	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clieneService;
	
	@Autowired
	private OrdemServRepository repository;
	
	public OrdemServ findById(Integer id) {
		Optional<OrdemServ> obj = repository.findById(id);
		
		return obj.orElseThrow(()-> new ObjectNotFaundExption("Objecto não encotrado! Id: "+ id +"Tipo"
				+OrdemServ.class.getName()));
	}
	
	public List<OrdemServ> findAll(){
		return repository.findAll();
	}

	public OrdemServ create(@Valid OrdemservDTO obj) {

		return fromDTO(obj);
	
	}
	
	public OrdemServ update(@Valid OrdemservDTO obj) {

		findById(obj.getId());
		return  fromDTO(obj);
	}
	
	private OrdemServ fromDTO(OrdemservDTO obj) {
		OrdemServ newObj = new OrdemServ();
		
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		
		Cliente cli = clieneService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFecho(LocalDateTime.now());
		}
		
		return repository.save(newObj);
		
	}

	
}
