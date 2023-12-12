package com.api.api_vinos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.Vino_DatosTecnicos_aux;
import com.api.api_vinos.repository.ConexionBD;


@Service
public class ConsultasJDBCService {

	@Autowired
	protected ConexionBD bbddRepositorio;
	
	//Metodo que tira de repositorio para la recogida de datos de los vinos
	public ArrayList<Vino_DatosTecnicos_aux> sacarDatosVino() {
		
		return bbddRepositorio.sacarDatosVino();
	}
	
	//Metodo que tira de repositorio para la recogida de los vinos segun el pais
	public ArrayList<String> sacarVinosPorPais(String pais) {
		
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		for(int x = 0; x < listaNombres.size(); x++) {
			System.out.println(listaNombres.get(x));
		}
		
		return listaNombres;
	}
	
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////

	public String comprobarNuevaInsercion() {
		
		return null;
	}
										////////NO TERMINADO////////
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
		
}
