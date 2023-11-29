package com.api.api_vinos.entity;

import lombok.Data;

@Data
public class ZonaDTO {
    String pais;
    String region;
    
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