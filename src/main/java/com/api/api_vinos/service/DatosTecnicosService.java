package com.api.api_vinos.service;

import java.util.Set;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.entity.ResponseDTO;

public interface DatosTecnicosService {
	
	// setDatosTecnicos necesitaria la url del modelo vino
	Set<DatosTecnicosDTO> setDatosTecnicos(ResponseDTO responseDTO);
	
	Set<DatosTecnicosDTO> getDatosTecnicosById(String idModeloVino);
	
	Set<DatosTecnicosDTO> getVinosPorPaises(String pais);
	
	Set<DatosTecnicosDTO> getVinosPorRegiones(String regiones);

}
