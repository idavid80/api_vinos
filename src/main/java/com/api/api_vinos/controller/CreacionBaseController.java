package com.api.api_vinos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.api_vinos.service.CreacionBaseDatosService;

@Controller
public class CreacionBaseController {

	@Autowired
	protected CreacionBaseDatosService conexionService;
	@ResponseBody
	@GetMapping("/crearBaseDatos")
	public String crearBaseDatos() {
		conexionService.crearBase();
		return "Base de datos creada";
	}
}
