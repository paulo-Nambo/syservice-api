package com.paulo.syservice.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.paulo.syservice.domain.Pessoa;
import com.paulo.syservice.domain.Tecnico;
import com.paulo.syservice.dtos.TecnicoDTO;
import com.paulo.syservice.repositories.PessoaRepository;
import com.paulo.syservice.repositories.TecnicoRepository;
import com.paulo.syservice.service.exption.ObjectNotFaundExption;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {

		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFaundExption(
				"Objecto não encotrado! Id:" + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	/*
	 * Cria um tecnico
	 */
	public Tecnico create(TecnicoDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("BI já cadastrado na base de dados");
		}

		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getBi(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza um tecnico
	 */

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {

		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("BI já cadastrado na base de dados");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setBi(objDTO.getBi());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);

	}
	/*
	 * Deletar um tecnico por ID
	 */

	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegrityViolationException("Tecnico contem ordem de serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Buscar um tecnico pelo BI
	 */

	private Pessoa findByCPF(TecnicoDTO objDto) {
		Pessoa obj = pessoaRepository.findByCPF(objDto.getBi());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
