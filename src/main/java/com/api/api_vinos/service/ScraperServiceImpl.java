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

	@Override
	public Set<ResponseDTO> getVinoPorPagina(String pagina) {

		// Set para almacenar elementos únicos
		Set<ResponseDTO> responseDTOS = new HashSet<>();
		// Pasando por las urls

		// metodo para extraer datos de wineissocial.com
		extraerDatosWineIsSocial(responseDTOS, pagina);

		return responseDTOS;
	}

	@Override
	public Set<ResponseDTO> getTodosLosVino() {

		String url = "https://wineissocial.com/1719-vinos?page=";
		// Set para almacenar elementos únicos
		Set<ResponseDTO> responseDTOS = new HashSet<>();
		// Pasando por las urls
		int numeroPaginaHTML = getNumeroPaginasHTML(url);
		// metodo para extraer datos de wineissocial.com
		for (int i = 1; i < numeroPaginaHTML; i++) {
			extraerDatosWineIsSocial(responseDTOS, String.valueOf(i));
		}

		return responseDTOS;
	}

	@Override
	public Set<ResponseDTO> getVinoDesdeHasta(String desde, String hasta) {
		int inicio = Integer.parseInt(desde);
		int fin = Integer.parseInt(hasta);
		// Set para almacenar elementos únicos
		Set<ResponseDTO> responseDTOS = new HashSet<>();
		// Pasando por las urls
		for (int paginaHTML = inicio; paginaHTML < fin; paginaHTML++) {

			System.out.println("Extrayendo... Pagina: " + inicio + "/" + fin);
			// metodo para extraer datos de wineissocial.com
			extraerDatosWineIsSocial(responseDTOS, String.valueOf(paginaHTML));
		}
		return responseDTOS;
	}

	private int getNumVinoPorPagina(String url) {

		int vinoPorPagina = 0;
		try {
			Document document = Jsoup.connect(url).get();
			vinoPorPagina = document.getElementsByClass("product-meta").size();

			System.out.println("Esta pagina tiene " + vinoPorPagina + " modelos de vinos");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return vinoPorPagina;
	}

	private int getNumeroPaginasHTML(String url) {

		int paginasHTML = 1;
		try {
			Document document = Jsoup.connect(url + paginasHTML).get();
			String obtenerNumeroPagina = document.getElementsByAttributeValue("rel", "nofollow").last().text();
			paginasHTML = Integer.parseInt(obtenerNumeroPagina);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return paginasHTML;
	}

	private void extraerDatosWineIsSocial(Set<ResponseDTO> responseDTOS, String paginaHTML) {

		String url = "https://wineissocial.com/1719-vinos?page=";

		try {
			Document document = Jsoup.connect(url + paginaHTML).get();
			int vinoPorPagina = getNumVinoPorPagina(url + paginaHTML);
			// System.out.println("Extrayendo " + vinoPorPagina + " vinos pagina " +
			// paginaHTML + "/" + numeroPaginaHTML);
			for (int i = 0; i < vinoPorPagina; i++) {

				Element element = document.getElementsByClass("product-meta").get(i);

				// obtener todos los elementos con la clase donde estan los datos para la BBDD
				Elements elements = element.getElementsByTag("a");

				// Obtener elementos de los elementos html
				for (Element modelos : elements) {
					ResponseDTO responseDTO = new ResponseDTO();

					if (!StringUtils.isEmpty(modelos.text())) {
						// mapping data to the model class
						responseDTO.setModeloVino(modelos.text());
						responseDTO.setUrl(modelos.attr("href"));
					}
					if (responseDTO.getUrl() != null) {
						responseDTOS.add(responseDTO);
					}
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

}
