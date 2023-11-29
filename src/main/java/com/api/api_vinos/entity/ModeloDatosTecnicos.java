package com.api.api_vinos.entity;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="datos_tecnicos")
@Data
public class ModeloDatosTecnicos {
	
	@Id
	private Long id;
	
	@NotNull
	private String pais;
	
	@NotNull
	private String region;

}