package com.api.api_vinos.entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="api_vino") 
public class ModeloVino implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String modelo;
    private String url;
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Modelo: " + modelo + ";");
        buffer.append("URL: " + url + ";");
        return buffer.toString();
    }
}

/*
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "api_vino")
@Data
public class ModeloVino {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String modelo;

	@NotNull
	private String url;

	@NotNull
	private boolean disponible;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "datos_tecnicos", joinColumns = @JoinColumn(name = "vino_id"), inverseJoinColumns = @JoinColumn(name = "datos_tecnicos_id"))
	private List<ModeloDatosTecnicos> datosTecnicos;

	public ModeloVino() {
		this.disponible = true;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public List<ModeloDatosTecnicos> getDatosTecnicos() {
		return datosTecnicos;
	}

	public void setDatosTecnicos(List<ModeloDatosTecnicos> datosTecnicos) {
		this.datosTecnicos = datosTecnicos;
	}
}
*/
