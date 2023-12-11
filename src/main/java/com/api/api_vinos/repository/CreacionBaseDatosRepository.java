package com.api.api_vinos.repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

/*

@Repository
public class CreacionBaseDatosRepository {
	
	public ConexionRepository conexionRepository;
	private Connection conexion;
	
	public void creacionBaseDatos() {
		
		conexion = conexionRepository.abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate("create database api_vinos;"
					+ "	use prueba;"
					+ "	CREATE TABLE Paises("
					+ "		ID int auto_increment PRIMARY KEY,"
					+ "		Nombre varchar(250) NOT NULL);"
					+ "	CREATE TABLE DenominacionOrigen("
					+ "		ID int auto_increment PRIMARY KEY,"
					+ "		Nombre varchar(250) NOT NULL,"
					+ "		IDPais int NOT NULL,"
					+ "		CONSTRAINT fk_pais FOREIGN key(IDPais) REFERENCES Paises(ID));"
					+ "	CREATE TABLE Vinos("
					+ "		ID int auto_increment PRIMARY KEY,"
					+ "		Nombre varchar(250) NOT NULL,"
					+ "		URL varchar(500) NOT NULL unique,"
					+ "		Imagen varchar(500) null,"
					+ "		IDPais int NOT NULL,"
					+ "		IDDenominacionOrigen int NOT NULL,"
					+ "		CONSTRAINT fk_pais_datosvino FOREIGN KEY(IDPais) REFERENCES Paises(ID),"
					+ "		CONSTRAINT fk_denominacionOrigen_datosvino  FOREIGN KEY(IDDenominacionOrigen) REFERENCES DenominacionOrigen(ID));");
			conexionRepository.cerrarConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearBaseDatos() {
		
		bbddRepositorio.crearBaseDatos();
	}
	
	public void crearTablaVinos() {
		bbddRepositorio.crearTablaVinos();
	}
	
	public void crearTablaDatosTecnicos() {
		bbddRepositorio.crearTablaDatosTecnicos();
	}
	
}
*/