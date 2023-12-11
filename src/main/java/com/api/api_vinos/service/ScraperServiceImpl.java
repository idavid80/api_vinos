package com.api.api_vinos.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.VinoDTO;
import com.api.api_vinos.repository.ConexionBD;

@Service
public class ScraperServiceImpl implements ScraperService {

	@Autowired
	protected ConexionBD repo;

	@Override
	public Set<VinoDTO> getVinoPorPagina(String pagina) {

		// Set para almacenar elementos únicos
		Set<VinoDTO> vinoDTOS = new HashSet<>();
		// Pasando por las urls

		// metodo para extraer datos de wineissocial.com
		extraerDatosWineIsSocial(vinoDTOS, pagina);

		return vinoDTOS;
	}

	@Override
	public Set<VinoDTO> getTodosLosVino() {

		String url = "https://wineissocial.com/1719-vinos?page=";
		// Set para almacenar elementos únicos
		Set<VinoDTO> vinoDTOS = new HashSet<>();
		// Pasando por las urls
		int numeroPaginaHTML = getNumeroPaginasHTML(url);
		// metodo para extraer datos de wineissocial.com
		for (int i = 1; i < numeroPaginaHTML; i++) {
			extraerDatosWineIsSocial(vinoDTOS, String.valueOf(i));
		}

		return vinoDTOS;
	}

	@Override
	public Set<VinoDTO> getVinoDesdeHasta(String desde, String hasta) {
		int inicio = Integer.parseInt(desde);
		int fin = Integer.parseInt(hasta);
		// Set para almacenar elementos únicos
		Set<VinoDTO> vinoDTOS = new HashSet<>();
		// Pasando por las urls
		for (int paginaHTML = inicio; paginaHTML < fin; paginaHTML++) {

			System.out.println("Extrayendo... Pagina: " + inicio + "/" + fin);
			// metodo para extraer datos de wineissocial.com
			extraerDatosWineIsSocial(vinoDTOS, String.valueOf(paginaHTML));
		}
		return vinoDTOS;
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

	private void extraerDatosWineIsSocial(Set<VinoDTO> vinoDTOS, String paginaHTML) {

		String url = "https://wineissocial.com/1719-vinos?page=";

		try {
			Document document = Jsoup.connect(url + paginaHTML).get();
			int vinoPorPagina = getNumVinoPorPagina(url + paginaHTML);

			for (int i = 0; i < vinoPorPagina; i++) {
				System.out.println("Extrayendo " + vinoPorPagina + " vinos pagina: " + i + "/" + vinoPorPagina);
				//
				Element element = document.getElementsByClass("product-meta").get(i);
				Element imagenes = document.getElementsByClass("product-image").get(i);
				// obtener todos los elementos con la clase donde estan los datos para la BBDD
				Elements elements = element.getElementsByTag("a");
				Elements imagen = imagenes.getElementsByClass("img-fluid");
				// Obtener elementos de los elementos html

				// product-image
				for (Element modelos : elements) {
					VinoDTO vinoDTO = new VinoDTO();

					if (!StringUtils.isEmpty(modelos.text())) {

						// Insertando modelo y url
						vinoDTO.setModeloVino(modelos.text());
						vinoDTO.setUrl(modelos.attr("href"));
						// Insertando url de la imagen
						vinoDTO.setImagen(imagen.attr("data-src"));
					}
					if (vinoDTO.getUrl() != null) {
						vinoDTOS.add(vinoDTO);
					}
				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

	public int insertarVino(List<VinoDTO> listaVinos) {

		repo.abrirConexion();

		int vinosIntroducidos = 0;
		for (VinoDTO vino : listaVinos) {
			repo.insertarVino(vino);

			vinosIntroducidos++;

		}

		repo.cerrarConexion();

		return vinosIntroducidos;

	}

	public List<VinoDTO> getAllVinos() {
		return repo.getAllVinos();
	}

	public int insertaVinosPorPagHTML(String pagina) {

		List<VinoDTO> listaVinos = hashToList(getVinoPorPagina(pagina));

		int vinosIntroducidos = insertarVino(listaVinos);

		return vinosIntroducidos;
	}

	public List<VinoDTO> hashToList(Set<VinoDTO> hash) {

		List<VinoDTO> lista = new ArrayList<>();

//		int numeroVinos = hash.size();
		for (VinoDTO vino : hash) {
			lista.add(vino);
		}

		return lista;
	}

	// DatosTecnicosDTO
	@Override
	public int insertaDatosTecnicosBD() {

		// Obtenemos lista de los datos tecnicos que hay en la base de datos
		List<DatosTecnicosDTO> listaDatos = getListDatosTecnicosDTO();

		int vinosIntroducidos = 0;

		for (DatosTecnicosDTO datosTecnicos : listaDatos) {
			repo.insertarDatosTecnicos(datosTecnicos);

			vinosIntroducidos++;
		}

		return vinosIntroducidos;
	}

	public List<DatosTecnicosDTO> getListDatosTecnicosDTO() {

		List<DatosTecnicosDTO> lista = new ArrayList<>();

		// Obtenemos una lista de vinos que hay en la Base Datos
		List<VinoDTO> listaVinos = repo.getAllVinos();

//		int numeroVinos = hash.size();
		for (VinoDTO vino : listaVinos) {
			// Obtenemos una lista con los datos tecnicos de los vinos
			lista.add(getDatosTecnicos(vino));
		}

		return lista;
	}

	public DatosTecnicosDTO getDatosTecnicos(VinoDTO vino) {

		DatosTecnicosDTO datosTecnicosDTO = new DatosTecnicosDTO();

		// metodo para extraer los datos tecnicos de cada vino
		extraerDatosTecnicos(datosTecnicosDTO, vino);

		return datosTecnicosDTO;
	}

	private void extraerDatosTecnicos(DatosTecnicosDTO dato, VinoDTO vino) {

		try {

			// Buscamos en la url de datos tecnicos del modelo de vino
			Document document = Jsoup.connect(vino.getUrl()).get();

			// Obtener elementos de los lista datos tecnicos del html
			Element element = document.getElementsByClass("especial izda col-xs-6").first();

			if (!StringUtils.isEmpty(element.getElementsByClass("pais").text())) {
				// Introducimos los datos tecnicos de cada vino
				dato.setIdModeloVino(vino.getIdModeloVino());
				dato.setPais(element.getElementsByClass("pais").text().replace("País: ", ""));
				dato.setRegion(element.getElementsByClass("denominacion").text().replace("Zona/D.O.: ", ""));
			}
		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}
}
/*
 * 
 * 
 * public void setDatosTecnicos(DatosTecnicosDTO datosTecnicos, String url) {
 * 
 * extraerDatosTecnicos(datosTecnicos, url);
 * System.out.println("setDatosTecnicos" + datosTecnicos);
 * 
 * }
 * 
 * private void extraerDatosTecnicos(DatosTecnicosDTO datos, String url) {
 * 
 * datos = new DatosTecnicosDTO();
 * 
 * // Obtenemos el ID del modelo vino
 * 
 * 
 * try {
 * 
 * // Buscamos en la url de datos tecnicos del modelo de vino Document document
 * = Jsoup.connect(url).get();
 * 
 * // Obtener elementos de los lista datos tecnicos del html Element element =
 * document.getElementsByClass("especial izda col-xs-6").first();
 * 
 * datos.setPais(element.getElementsByClass("pais").text().replace("País: ",
 * "")); datos.setRegion(element.getElementsByClass("denominacion").text().
 * replace("Zona/D.O.: ", ""));
 * 
 * } catch (IOException ex) { ex.printStackTrace();
 * 
 * } System.out.println("extraerDatosTecnicos" + datos); }
 * 
 * public DatosTecnicosDTO rellenarDatosTecnicosDTO(VinoDTO vino) {
 * DatosTecnicosDTO datosTecnicos = new DatosTecnicosDTO();
 * 
 * datosTecnicos.setIdModeloVino(vino.getIdModeloVino());
 * 
 * extraerDatosTecnicos(datosTecnicos, vino.getUrl());
 * 
 * System.out.println("rellenarDatosTecnicosDTO" + datosTecnicos); return
 * datosTecnicos;
 * 
 * 
 * } public List<DatosTecnicosDTO> insertarDatosTecnicosBD() {
 * 
 * List<DatosTecnicosDTO> listaDTO = new ArrayList<>(); // Obtenemos una lista
 * de vinos desde la base Datos
 * 
 * List<VinoDTO> listavinos = repo.getAllVinos();
 * 
 * System.out.println("insertarDatosTecnicosBD" + listavinos); try {
 * 
 * 
 * for (VinoDTO vino : listavinos) {
 * 
 * listaDTO.add(rellenarDatosTecnicosDTO(vino)); } } catch (Exception ex) {
 * ex.printStackTrace();
 * 
 * }
 * 
 * 
 * 
 * // repo.insertarDatosTecnicos(datosTecnicosDTO);
 * 
 * return listaDTO;
 * 
 * }
 */
