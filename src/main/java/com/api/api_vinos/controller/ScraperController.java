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

    @GetMapping(path = "/{pagina}")
    public Set<ResponseDTO> getVehicleByModel(@PathVariable String pagina) {
        return  scraperService.getModeloVino(pagina);
    }
}