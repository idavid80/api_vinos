package com.api.api_vinos.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.api_vinos.service.ScraperService;

@Repository
public class ConexionModeloVino {

    @Autowired
    ScraperService scraperService;
    
    
}
