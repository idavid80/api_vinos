package com.api.api_vinos.service;

import java.util.Set;

import com.api.api_vinos.entity.ZonaDTO;


public interface ZonaService {
    Set<ZonaDTO> getZona(String zona);
}
