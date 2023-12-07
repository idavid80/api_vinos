package com.api.api_vinos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_vinos.repository.CreacionBaseDatosRepository;

@Service
public class CreacionBaseDatosService {
	
	@Autowired
	protected CreacionBaseDatosRepository bbddRepositorio;
	
	public void crearBase() {
		bbddRepositorio.creacionBaseDatos();
	}
}
