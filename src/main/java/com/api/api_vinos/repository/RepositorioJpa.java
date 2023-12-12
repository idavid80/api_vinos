package com.api.api_vinos.repository;

import org.springframework.data.repository.CrudRepository;

import com.api.api_vinos.entity.VinoJPA;


public interface RepositorioJpa extends CrudRepository<VinoJPA, Integer>{
	
}
