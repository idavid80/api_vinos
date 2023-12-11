package com.api.api_vinos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.api_vinos.service.ExportacionesService;

@Controller
public class ExportacionesController {

	@Autowired
	protected ExportacionesService conexionService;
	
	@ResponseBody
    @GetMapping("/generarXML")
	public void generarXML() {
		conexionService.generarXML();
	}
}
