package com.api.api_vinos.entity;

public class Vino_DatosTecnicos_aux {

	public int id;
	public String nombre;
	public String pais;
	public String region;
	public String url;
	
	
	public Vino_DatosTecnicos_aux() {}

	
	

	public Vino_DatosTecnicos_aux(int id, String nombre, String pais, String region, String url) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.region = region;
		this.url = url;
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


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
