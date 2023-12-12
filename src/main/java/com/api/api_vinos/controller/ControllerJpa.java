package com.api.api_vinos.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.api_vinos.entity.VinoJPA;
import com.api.api_vinos.repository.RepositorioJpa;

@Controller
@RequestMapping(path = "/")
public class ControllerJpa {

	@Autowired
	RepositorioJpa repositorioJpa;

	@GetMapping(path = "/jpa/conexion")
	public String comprobarConexion() {
		return "Controlador funcionando correctamente";
	}

	@GetMapping(path = "/jpa/listar-vinos")
	public @ResponseBody Iterable<VinoJPA> getAll() {

		return repositorioJpa.findAll();

	}

	@GetMapping(path = "/jpa/vino{id}")
	public @ResponseBody Optional<VinoJPA> getModeloById(@PathVariable String id) {

		return repositorioJpa.findById(1);
	}

	@GetMapping(path = "/jpa/contar")
	public @ResponseBody long contarVinos() {

		return repositorioJpa.count();
	}

	@GetMapping(path = "/jpa/existe")
	public @ResponseBody boolean getPrueba() {

		return repositorioJpa.existsById(2);
	}

}
