package com.api.api_vinos.controller;

import java.util.List;
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
    
    
    @GetMapping(path = "/datos-tecnicos/prueba")	//Prueba de conexion
    public String comprobarConexion() {
        return "Controlador funcionando correctamente";
    }
    
    @GetMapping(path = "/datos-tecnicos/getDatosTecnicosDTOPorPagina")	//Metodo que devuelve datos tecnicos desde la pagina web a la base
    Set<DatosTecnicosDTO> getDatosTecnicosDTOPorPagina(@PathVariable String pagina){
    	return datosTecnicos.getDatosTecnicosDTOPorPagina(pagina);
    }
   
}