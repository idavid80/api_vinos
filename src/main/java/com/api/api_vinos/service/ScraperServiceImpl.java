package com.api.api_vinos.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.ResponseDTO;

@Service
public class ScraperServiceImpl implements ScraperService {
	// Lectura de datos de un fichero de propiedades a una lista
	@Value("#{'${website.urls}'.split(',')}")
	List<String> urls;

	@Override
	public Set<ResponseDTO> getModeloVino(String pagina) {
		// Set para almacenar elementos únicos
		Set<ResponseDTO> responseDTOS = new HashSet<>();
		// Pasando por las urls
		for (String url : urls) {

			if (url.contains("wineissocial")) {
				// metodo para extraer datos de wineissocial.com
				extraerDatosWineIsSocial(responseDTOS, url + pagina);
			}

		}

		return responseDTOS;
	}

	private void extraerDatosWineIsSocial(Set<ResponseDTO> responseDTOS, String url) {

		int vinoPorPagina = 40;

		try {

			// cargando el HTML en un objeto Document
			Document document = Jsoup.connect(url).get();
			// Seleccionando el elemento que contiene la lista de modelos de vinos

			for (int i = 0; i < vinoPorPagina; i++) {
				Element element = document.getElementsByClass("product-meta").get(i);
				// obteniendo todos los elementos con la clase donde estan los datos para la
				// BBDD
				Elements elements = element.getElementsByTag("a");
				// Obtener elementos de los elementos html
				for (Element modelos : elements) {
					ResponseDTO responseDTO = new ResponseDTO();

					if (!StringUtils.isEmpty(modelos.text())) {
						// mapping data to the model class
						responseDTO.setModeloVino(modelos.text());
						responseDTO.setUrl(modelos.attr("href"));
					}
					if (responseDTO.getUrl() != null)
						responseDTOS.add(responseDTO);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
