package com.api.api_vinos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.api_vinos.entity.VinoDTO;
import com.api.api_vinos.service.CreacionBaseDatosService;

@RestController
@RequestMapping(path = "/")
public class CreacionBaseController {

	@Autowired
	CreacionBaseDatosService conexionService;

    @GetMapping(path = "/bbdd/crear-db")	//Metodo de creacion de base de datos
    public String crearBaseDatos() {
		conexionService.crearBaseDatos();
        return "Base datos creada con exito";
    }

    @GetMapping(path = "/bbdd/crear-tabla-vino")	//Metodo de creacion de tabla de vino
    public String crearTablaVinos() {
		conexionService.crearTablaVinos();
        return "Tabla Vinos creada con exito";
    }

    @GetMapping(path = "/bbdd/crear-tabla-datos-tecnicos")	//Metodo de creacion de tabla de datos por vino
    public String crearTablaDatosTecnicos() {
		conexionService.crearTablaDatosTecnicos();
        return "Tabla Datos Tecnicos creada con exito";
    }
    
    @GetMapping(path = "/bbdd/get-all")	//Metodo que retorna una lista con todos los vinos y sus datos
    public List<VinoDTO> getAllVinos() {
		return conexionService.getAllVinos();

    }
    
    
}
