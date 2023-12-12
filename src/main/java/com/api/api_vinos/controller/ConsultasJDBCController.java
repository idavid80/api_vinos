package com.api.api_vinos.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.api_vinos.entity.Vino_DatosTecnicos_aux;
import com.api.api_vinos.service.ConsultasJDBCService;

@Controller
public class ConsultasJDBCController {

	@Autowired
	protected ConsultasJDBCService conexionService;
	
	
	@ResponseBody
	@GetMapping("/mostrarDatosVino")	//Metodo que devuelve por postman un ArrayList con los datos de los vinos
	public ArrayList<Vino_DatosTecnicos_aux> mostrarDatosVino() {
		return conexionService.sacarDatosVino();
	}
	
	@ResponseBody
	@GetMapping("/mostrarVinosPorPais")	//Metodo que devuelve por postman un ArrayList con vinos segun un pais introducido
	public ArrayList<String> mostrarVinosPorPais(String pais) {
		return conexionService.sacarVinosPorPais(pais);
	}
	
}
