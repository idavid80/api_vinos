package com.api.api_vinos.service;

import java.util.Set;

import com.api.api_vinos.entity.DatosTecnicosDTO;

public interface DatosTecnicosService {

	Set<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(String pagina);

}
