package com.api.api_vinos.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.ResponseDTO;

@Service
public class DatosTecnicosServiceImpl implements DatosTecnicosService {

	
	
	@Override
	public Set<DatosTecnicosDTO> setDatosTecnicos(ResponseDTO responseDTO) {
		// Set para almacenar elementos únicos
		Set<DatosTecnicosDTO> datosTecnicosDTO = new HashSet<>();
		// Obtenemos el id del ModeloVino
		int idModeloVino = responseDTO.getIdModeloVino();
		// metodo para extraer datos de wineissocial.com
		obtenerDatosTecnicosById(datosTecnicosDTO, idModeloVino);

		return datosTecnicosDTO;
	}

	@Override
	public Set<DatosTecnicosDTO> getVinosPorPaises(String pais) {

		// Set para almacenar elementos únicos
		Set<DatosTecnicosDTO> datosTecnicosDTO = new HashSet<>();
		// Pasando por las urls

		// metodo para extraer datos de wineissocial.com
		// extraerDatosTecnicosURL(datosTecnicosDTO, pais);
		obtenerDatosTecnicosById(datosTecnicosDTO, 1);
		return datosTecnicosDTO;
	}

	@Override
	public Set<DatosTecnicosDTO> getVinosPorRegiones(String regiones) {

		// Set para almacenar elementos únicos
		Set<DatosTecnicosDTO> datosTecnicosDTO = new HashSet<>();
		// Pasando por las urls

		// metodo para extraer datos de wineissocial.com
		obtenerDatosTecnicosById(datosTecnicosDTO, 0);

		return datosTecnicosDTO;
	}
	
	public Set<DatosTecnicosDTO> getDatosTecnicosById(String idModeloVino) {
		
		int id = Integer.parseInt(idModeloVino);
		// Set para almacenar elementos únicos
		Set<DatosTecnicosDTO> datosTecnicosDTO = new HashSet<>();
		// select modelo_vino id = idModeloVino
		ResponseDTO modeloVino = new ResponseDTO();
		// Comprobamos que el id que recibimos coincide con el de la base de datos
		
		obtenerDatosTecnicosById(datosTecnicosDTO, 0);;
		/*
		if(modeloVino.getIdModeloVino() == id) {
			obtenerDatosTecnicosById(datosTecnicosDTO, id);
		};
*/
		// metodo para extraer datos de wineissocial.com
		// extraerDatosTecnicosURL(datosTecnicosDTO, regiones);

		return datosTecnicosDTO;
	}
	
	
	private void obtenerDatosTecnicosById(Set<DatosTecnicosDTO> datosTecnicosDTOS, int idModeloVino) {
		
		ResponseDTO modeloVino = new ResponseDTO();
		idModeloVino = 0;
		//String url = modeloVino.getUrl();
		
		// url prueba		
		String url = "https://wineissocial.com/1000069-26761-chivite-coleccion-125-blanco-vino-blanco.html#/172-anada-2019";

		extraerDatosTecnicosURL(datosTecnicosDTOS, url);
	}
	
	private void extraerDatosTecnicosURL(Set<DatosTecnicosDTO> datosTecnicosDTO, String url) {

		try {
			Document document = Jsoup.connect(url).get();

			// Obtener elementos de los lista datos tecnicos del html
			Element element = document.getElementsByClass("especial izda col-xs-6").first();


			DatosTecnicosDTO datos = new DatosTecnicosDTO();
			
			
			datos.setPais(element.getElementsByClass("pais").text().replace("País: ", ""));
			datos.setRegion(element.getElementsByClass("denominacion").text().replace("Zona/D.O.: ", ""));

			if (datos.getPais() != null) {
				datosTecnicosDTO.add(datos);
			}
		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}
	
}
