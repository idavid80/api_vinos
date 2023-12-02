package com.api.api_vinos.controller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_vinos.entity.ResponseDTO;
import com.api.api_vinos.service.ScraperService;



@RestController
@RequestMapping(path = "/")
public class ScraperController {

    @Autowired
    ScraperService scraperService;

    @GetMapping(path = "/prueba")
    public String comprobarConexion() {
        return "Controlador funcionando correctamente";
    }
    @GetMapping(path = "/{pagina}")
    public Set<ResponseDTO> getVinoPorPagina(@PathVariable String pagina) {
    	
        return  scraperService.getVinoPorPagina(pagina);
    }
    
    @GetMapping(path = "/obtener_vinos{desde}{hasta}")
    public Set<ResponseDTO> getVinoDesdeHasta(@PathVariable String desde, @PathVariable String hasta) {

        return  scraperService.getVinoDesdeHasta(desde, hasta);
    }
    
    @GetMapping(path = "/todos_los_vinos")
    public Set<ResponseDTO> getTodosLosVino() {


        return  scraperService.getTodosLosVino();
    }
}