package com.paulo.syservice.rosources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paulo.syservice.dtos.OrdemservDTO;
import com.paulo.syservice.service.OrdemservService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordemserv")
public class OrdemservResource {

	@Autowired
	private OrdemservService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemservDTO> findById(@PathVariable Integer id) {
		OrdemservDTO obj = new OrdemservDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<OrdemservDTO>> findAll() {
		List<OrdemservDTO> list = service.findAll().stream().map(obj -> new OrdemservDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrdemservDTO> create(@Valid @RequestBody OrdemservDTO obj) {

		obj = new OrdemservDTO(service.create(obj));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public ResponseEntity<OrdemservDTO> update(@Valid @RequestBody OrdemservDTO obj) {
		obj = new OrdemservDTO(service.update(obj));
		return ResponseEntity.ok().body(obj);
	}
}
