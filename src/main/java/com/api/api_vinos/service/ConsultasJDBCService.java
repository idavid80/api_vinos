package com.api.api_vinos.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.Vino;
import com.api.api_vinos.repository.ConsultasJDBCRepository;


@Service
public class ConsultasJDBCService {

	@Autowired
	protected ConsultasJDBCRepository bbddRepositorio;
	
	
	public ArrayList<Vino> sacarDatosVino() {
		
		ArrayList<Vino> listaVinos = new ArrayList<Vino>();
		listaVinos = bbddRepositorio.sacarDatosVino();
		
		
		
		return listaVinos;
	}
	
	public ArrayList<String> sacarVinosPorPais(String pais) {
		
		ArrayList<String> listaNombres = new ArrayList<String>();
		listaNombres = bbddRepositorio.sacarVinosPorPais(pais);
		
		for(int x = 0; x < listaNombres.size(); x++) {
			System.out.println(listaNombres.get(x));
		}
		
		return listaNombres;
	}
		
}
