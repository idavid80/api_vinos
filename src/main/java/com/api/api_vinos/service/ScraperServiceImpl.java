package com.api.api_vinos.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

@Service	//En esta clase encontramos los distintos metodos necesarios para el web scrapping (implementa de la interfaz ScraperService)
public class ScraperServiceImpl implements ScraperService {

	@Autowired
	protected ConexionBD repo;

	@Override
	public Set<VinoDTO> getVinoPorPagina(String pagina) {

		// Set para almacenar elementos únicos
		Set<VinoDTO> vinoDTOS = new HashSet<>();

		// metodo para extraer datos de wineissocial.com
		extraerDatosWineIsSocial(vinoDTOS, pagina);

		return vinoDTOS;
	}

	@Override
	public Set<VinoDTO> getTodosLosVino() {

		String url = "https://wineissocial.com/1719-vinos?page=";
		// Set para almacenar elementos únicos
		Set<VinoDTO> vinoDTOS = new HashSet<>();

		// Obtener el numero de paginasa del html
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

			// obtiene un string con el último numero de paginacion del html
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

			// obtenemos el numero de vino que hay en una pagina
			int vinoPorPagina = getNumVinoPorPagina(url + paginaHTML);

			for (int i = 0; i < vinoPorPagina; i++) {

				// div donde se encuentran los datos tecnicos
				Element element = document.getElementsByClass("product-meta").get(i);

				// div donde se encuentra la imagen del vino
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

			// Ejecuta la sentancia sql para insertar en la base datos
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

		// Transformamos el set a una lista de vinos
		List<VinoDTO> listaVinos = hashToList(getVinoPorPagina(pagina));

		// Itera la lista de vinos en sentencia sql de insertar
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
	

	// Archivo File
	public String guardarImagen() {
		
		List<VinoDTO> listaVino = hashToList(getVinoPorPagina("3"));
		
		copiarImagen(listaVino.get(0).getImagen(), listaVino.get(0).getModeloVino());
		
		return "imagen " +listaVino.get(0).getModeloVino() + " guardada";
		
	}
	
	public void copiarImagen(String url, String modelo) {

		try {
		/* definimos la URL de la cual vamos a leer */

		URL obtenerURL = new URL(url);
		/* llamamos metodo para que lea de la URL y lo escriba en le fichero pasado */
		writeTo(obtenerURL.openStream(), new FileOutputStream(new File("./src/imagenes/" + modelo + ".jpg" )));

		System.out.println("Imagen leida y guardada!");

		} catch (MalformedURLException e) {
		e.printStackTrace();

		} catch (FileNotFoundException e) {
		e.printStackTrace();

		} catch (IOException e) {
		e.printStackTrace();

		}
		}


		public static void writeTo(InputStream in, OutputStream out) throws IOException {
		try {
		int c;
		while ((c = in.read()) != -1) {
		out.write(c);
		}

		} finally {
		if (in!= null) {
			try{ in.close(); 
			} catch (Exception e) {} }
		if (out!= null) { 
			try{ out.close();
			} catch (Exception e) {} }
		}

		return;
		}
	
	
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
	
	public void insertaVinosPorPagHTMLTimerTask(String pagina) {

		List<VinoDTO> listaVinos = hashToList(getVinoPorPagina(pagina));

		insertarVinoConTimerTask(listaVinos);

	}
	
	public void insertarVinoConTimerTask(List<VinoDTO> listaVinos) {
	
	repo.abrirConexion();
	
	int vinosIntroducidos = 0;
	for (VinoDTO vino : listaVinos) {
	repo.insertarVinoConTimerTask(vino);
	
	vinosIntroducidos++;
	
	}
	
	repo.cerrarConexion();
	
	}
	
	
	
	/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
}