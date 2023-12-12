package com.api.api_vinos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "datos_tecnicos")
public class DatosTecnicosJpa {
	@Id
	@Column(name="id_datos_tecnicos")
	int idDatosTecnicos;
	
	@Column(name="pais")
	String pais;
	
	@Column(name="region")
	String region;
	

	
}
