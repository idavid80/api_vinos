package com.api.api_vinos.entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DatosTecnicosDTO {
	int idDatosTecnicos;
	String pais;
	String region;
	int idModeloVino;
	
	@ManyToOne
	DatosTecnicosJpa datosTecnicosJpa;
	
	public DatosTecnicosJpa getDatosTecnicosJpa() {
		return datosTecnicosJpa;
	}
	public void setDatosTecnicosJpa(DatosTecnicosJpa datosTecnicosJpa) {
		this.datosTecnicosJpa = datosTecnicosJpa;
	}
	public int getIdDatosTecnicos() {
		return idDatosTecnicos;
	}
	public void setIdDatosTecnicos(int idDatosTecnicos) {
		this.idDatosTecnicos = idDatosTecnicos;
	}
	public int getIdModeloVino() {
		return idModeloVino;
	}
	public void setIdModeloVino(int idModeloVino) {
		this.idModeloVino = idModeloVino;
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

}
