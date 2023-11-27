package com.api.api_vinos.entity;

import lombok.Data;

@Data
public class ResponseDTO {
    String modeloVino;
    String url;
    
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