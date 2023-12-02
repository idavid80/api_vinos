package com.api.api_vinos.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_vinos.entity.DatosTecnicosDTO;
import com.api.api_vinos.service.DatosTecnicosService;
@RestController
@RequestMapping(path = "/")
public class DatosTecnicosController {

    @Autowired
    DatosTecnicosService datosTecnicos;

    @GetMapping(path = "/datos-tecnicos/prueba")
    public String comprobarConexion() {
        return "Controlador funcionando correctamente";
    }

    @GetMapping(path = "/datos-tecnicos/idModeloVino{idModeloVino}")
    public Set<DatosTecnicosDTO> getDatosTecnicosById(@PathVariable String idModeloVino) {
    	System.out.print("Controlador" + idModeloVino);
        return  datosTecnicos.getDatosTecnicosById(idModeloVino);
     
    }
    /*
    @GetMapping(path = "/datos-tecnicos/idVino{idModeloVino}")
    public String getDatosTecnicosById(@PathVariable String idModeloVino) {
    	System.out.print("Controlador" + idModeloVino);
        return  idModeloVino;
     
    }
     */
    @GetMapping(path = "/datos-tecnicos/region{region}")
    public Set<DatosTecnicosDTO> getVinosPorRegiones(@PathVariable String region) {
    	
        return  datosTecnicos.getVinosPorRegiones(region);
    }
    
    @GetMapping(path = "/datos-tecnicos/pais{pais}")
    public Set<DatosTecnicosDTO> getVinosPorRPaises(@PathVariable String pais) {
    	
        return  datosTecnicos.getVinosPorPaises(pais);
    }
    
}