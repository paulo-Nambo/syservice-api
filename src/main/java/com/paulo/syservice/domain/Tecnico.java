package com.paulo.syservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable	 {

	private static final long serialVersionUID=1L;
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico" )
	private List<OrdemServ> list = new ArrayList<>();
	
	public Tecnico() {
		super();
		
	}

	public Tecnico(Integer id, String nome, String bi, String telefone) {
		super(id, nome, bi, telefone);
		
	}

	public List<OrdemServ> getList() {
		return list;
	}

	public void setList(List<OrdemServ> list) {
		this.list = list;
	}
	

}
