package com.api.api_vinos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.VinoDTO;
import com.api.api_vinos.repository.ConexionBD;

@Service
public class CreacionBaseDatosService {

	@Autowired
	protected ConexionBD repo;

	public void crearBaseDatos() {

		repo.crearBaseDatos();
	}

	public void crearTablaVinos() {
		repo.crearTablaVinos();
	}

	public void crearTablaDatosTecnicos() {
		repo.crearTablaDatosTecnicos();
	}
	
	public List<VinoDTO> getAllVinos() {
		return repo.getAllVinos();
	}
	

}
