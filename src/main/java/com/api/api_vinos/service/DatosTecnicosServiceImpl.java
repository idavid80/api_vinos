package com.api.api_vinos.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.repository.ConexionBD;

@Service
public class DatosTecnicosServiceImpl implements DatosTecnicosService {

	protected ConexionBD repo;

	public Set<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(String pagina) {

		// Set para almacenar elementos únicos
		Set<DatosTecnicosDTO> datosTecnicos = new HashSet<>();
		// Pasando por las urls

		// metodo para extraer datos de wineissocial.com
		extraerDatosTecnicos(datosTecnicos, pagina);

		return datosTecnicos;
	}
	
	private void extraerDatosTecnicos(Set<DatosTecnicosDTO> datos, String url) {


		// Obtenemos el ID del modelo vino
		

		try {

			// Buscamos en la url de datos tecnicos del modelo de vino
			Document document = Jsoup.connect(url).get();

			// Obtener elementos de los lista datos tecnicos del html
			Element element = document.getElementsByClass("especial izda col-xs-6").first();


				DatosTecnicosDTO datoTecnicosDTO = new DatosTecnicosDTO();

				if (!StringUtils.isEmpty(element.getElementsByClass("pais").text())) {
					datoTecnicosDTO.setPais(element.getElementsByClass("pais").text().replace("País: ", ""));
					datoTecnicosDTO.setRegion(element.getElementsByClass("denominacion").text().replace("Zona/D.O.: ", ""));
				}
				if (datoTecnicosDTO.getPais() != null) {
					datos.add(datoTecnicosDTO);
				}
		} catch (IOException ex) {
			ex.printStackTrace();

		}
		System.out.println("extraerDatosTecnicos" + datos);
	}
}		
