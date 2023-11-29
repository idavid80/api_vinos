package com.api.api_vinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.api_vinos.entity.ModeloDatosTecnicos;

public interface DatosTecnicosRepository extends JpaRepository<ModeloDatosTecnicos, Long>{
	public ModeloDatosTecnicos findByPais(String Pais);
}
