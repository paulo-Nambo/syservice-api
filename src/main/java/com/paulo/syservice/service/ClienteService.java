package com.paulo.syservice.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.paulo.syservice.domain.Pessoa;
import com.paulo.syservice.domain.Cliente;
import com.paulo.syservice.dtos.ClienteDTO;
import com.paulo.syservice.repositories.PessoaRepository;
import com.paulo.syservice.repositories.ClienteRepository;
import com.paulo.syservice.service.exption.ObjectNotFaundExption;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {

		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFaundExption(
				"Objecto não encotrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * Cria um Cliente
	 */
	public Cliente create(ClienteDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("BI já cadastrado na base de dados");
		}

		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getBi(), objDTO.getTelefone()));
	}

	/*
	 * Atualiza um Cliente
	 */

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {

		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("BI já cadastrado na base de dados");
		}
		oldObj.setNome(objDTO.getNome());
		oldObj.setBi(objDTO.getBi());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);

	}
	/*
	 * Deletar um Cliente por ID
	 */

	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegrityViolationException("Cliente contem ordem de serviços, não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	/*
	 * Buscar um Cliente pelo BI
	 */

	private Pessoa findByCPF(ClienteDTO objDto) {
		Pessoa obj = pessoaRepository.findByCPF(objDto.getBi());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
