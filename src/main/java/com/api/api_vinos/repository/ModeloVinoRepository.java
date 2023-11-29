package com.api.api_vinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.api_vinos.entity.ModeloVino;

@Repository
public interface ModeloVinoRepository extends JpaRepository<ModeloVino, Long>{
	public ModeloVino getModeloVino(String modelo);
}