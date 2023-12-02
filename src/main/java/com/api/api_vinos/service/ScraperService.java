package com.api.api_vinos.service;

import java.util.Set;

import com.api.api_vinos.entity.ResponseDTO;

public interface ScraperService {
	// public Set<ResponseDTO> getModeloVino(String modeloVino);
	Set<ResponseDTO> getVinoPorPagina(String pagina);

	Set<ResponseDTO> getTodosLosVino();

	Set<ResponseDTO> getVinoDesdeHasta(String desde, String hasta);

}