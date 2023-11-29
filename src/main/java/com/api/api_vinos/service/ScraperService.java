package com.api.api_vinos.service;
import java.util.Set;

import com.api.api_vinos.entity.ResponseDTO;


public interface ScraperService {
    Set<ResponseDTO> getModeloVino(String modeloVino);
}

/*
 Obtener todos los modelos
public interface ScraperService {
    Set<ResponseDTO> getAllWines();
}
*/