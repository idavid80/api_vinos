package com.api.api_vinos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vinos")
public class VinoJPA {
	@Id
	@Column(name="id_vino")
	int id_vino;
	@Column(name="modelo")
	String modelo;
	
	@Column(name="url")
	String url;
	
	@Column(name="imagen")
	String imagen;

	@ManyToOne
	DatosTecnicosJpa datosTecnicosJpa;
	
	public DatosTecnicosJpa getDatosTecnicosJpa() {
		return datosTecnicosJpa;
	}
	public void setDatosTecnicosJpa(DatosTecnicosJpa datosTecnicosJpa) {
		this.datosTecnicosJpa = datosTecnicosJpa;
	}
	
	public VinoJPA(int id_vino, String modelo, String url, String imagen) {
		super();
		this.id_vino = id_vino;
		this.modelo = modelo;
		this.url = url;
		this.imagen = imagen;
	}
	
	public VinoJPA() {
	    // Default constructor
	}
	
	public int getId_vino() {
		return id_vino;
	}
	public void setId_vino(int id_vino) {
		this.id_vino = id_vino;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	
}
