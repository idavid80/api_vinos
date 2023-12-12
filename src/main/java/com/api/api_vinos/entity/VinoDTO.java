package com.api.api_vinos.entity;

import lombok.Data;

@Data
public class VinoDTO {
	int idModeloVino;

	String modeloVino;
	String url;
	String imagen;

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getIdModeloVino() {
		return idModeloVino;
	}

	public void setIdModeloVino(int idModeloVino) {
		this.idModeloVino = idModeloVino;
	}

	public String getModeloVino() {
		return modeloVino;
	}

	public void setModeloVino(String modeloVino) {
		this.modeloVino = modeloVino;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}