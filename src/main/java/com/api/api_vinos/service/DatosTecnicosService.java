package com.api.api_vinos.service;

import java.util.Set;

import com.api.api_vinos.entity.DatosTecnicosDTO;

public interface DatosTecnicosService {

	Set<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(String pagina);
	// setDatosTecnicos necesitaria la url del modelo vino
	//DatosTecnicosDTO setDatosTecnicos(DatosTecnicosDTO datosTecnicos, String url);
	
//	DatosTecnicosDTO getDatosTecnicosById(String idModeloVino);

//	DatosTecnicosDTO getVinosPorPaises(String pais);
	
//	DatosTecnicosDTO getVinosPorRegiones(String regiones);
	
//	List<DatosTecnicosDTO> insertarDatosTecnicosBD();

}
