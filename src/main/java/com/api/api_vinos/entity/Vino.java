package com.api.api_vinos.entity;

public class Vino {

	public int id;
	public String nombre;
	public String url;
	public String imagen;
	public int idPais;
	public int idDenominacionOrigen;
	
	
	
	public Vino() {}
	
	public Vino(int id, String nombre, String url, String imagen, int idPais, int idDenominacionOrigen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.url = url;
		this.imagen = imagen;
		this.idPais = idPais;
		this.idDenominacionOrigen = idDenominacionOrigen;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public int getIdDenominacionOrigen() {
		return idDenominacionOrigen;
	}

	public void setIdDenominacionOrigen(int idDenominacionOrigen) {
		this.idDenominacionOrigen = idDenominacionOrigen;
	}
	
}
