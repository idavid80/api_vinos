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
	/*
	@ResponseBody
	@GetMapping("/crearBaseDatos")
	public String crearBaseDatos() {
		conexionService.crearBase();
		return "Base de datos creada";
	}
*/
    @GetMapping(path = "/bbdd/crear-db")
    public String crearBaseDatos() {
		conexionService.crearBaseDatos();
        return "Base datos creada con exito";
    }

    @GetMapping(path = "/bbdd/crear-tabla-vino")
    public String crearTablaVinos() {
		conexionService.crearTablaVinos();
        return "Tabla Vinos creada con exito";
    }

    @GetMapping(path = "/bbdd/crear-tabla-datos-tecnicos")
    public String crearTablaDatosTecnicos() {
		conexionService.crearTablaDatosTecnicos();
        return "Tabla Datos Tecnicos creada con exito";
    }
    
    @GetMapping(path = "/bbdd/get-all")
    public List<VinoDTO> getAllVinos() {
		return conexionService.getAllVinos();

    }
    
    
}
