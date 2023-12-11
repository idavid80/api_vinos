package com.api.api_vinos.service;

import java.util.List;
import java.util.Set;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.VinoDTO;

public interface ScraperService {
	// public Set<ResponseDTO> getModeloVino(String modeloVino);
	Set<VinoDTO> getVinoPorPagina(String pagina);

	Set<VinoDTO> getTodosLosVino();

	Set<VinoDTO> getVinoDesdeHasta(String desde, String hasta);
	
	int insertaVinosPorPagHTML(String pagina);
	
	List<DatosTecnicosDTO> getListDatosTecnicosDTO();
	
	int insertaDatosTecnicosBD();

/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
	void insertaVinosPorPagHTMLTimerTask(String pagina);
/////////////////////////////// PARTE DE PROGRAMACION DE PROCESOS/////////////////////////////////////////////////////////////
	
	
	//Set<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(vinoDTOS vino);


}