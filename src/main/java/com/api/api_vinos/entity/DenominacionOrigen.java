package com.api.api_vinos.entity;

public class DenominacionOrigen {

	public int id;
	public String nombre;
	public int idPais;
	
	
	
	public DenominacionOrigen() {}
	
	public DenominacionOrigen(int id, String nombre, int idPais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idPais = idPais;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	
}
