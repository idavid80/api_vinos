package com.api.api_vinos.repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreacionBaseDatosRepository {
	
	public ConexionRepository conexionRepository;
	private Connection conexion;
	
	public void creacionBaseDatos() {
		
		conexion = conexionRepository.abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			stmt.execute("create database api_vinos;\r\n"
					+ "	\r\n"
					+ "	use prueba;\r\n"
					+ "	\r\n"
					+ "	CREATE TABLE Paises(\r\n"
					+ "		ID int auto_increment PRIMARY KEY,\r\n"
					+ "		Nombre varchar(250) NOT NULL\r\n"
					+ "	);\r\n"
					+ "	\r\n"
					+ "	CREATE TABLE DenominacionOrigen(\r\n"
					+ "		ID int auto_increment PRIMARY KEY,\r\n"
					+ "		Nombre varchar(250) NOT NULL,\r\n"
					+ "		IDPais int NOT NULL,\r\n"
					+ "		CONSTRAINT fk_pais FOREIGN key(IDPais) REFERENCES Paises(ID)\r\n"
					+ "	);\r\n"
					+ "	\r\n"
					+ "	CREATE TABLE Vinos(\r\n"
					+ "		ID int auto_increment PRIMARY KEY,\r\n"
					+ "		Nombre varchar(250) NOT NULL,\r\n"
					+ "		URL varchar(500) NOT NULL unique,\r\n"
					+ "		Imagen varchar(500) null\r\n"
					+ "		IDPais int NOT NULL,\r\n"
					+ "		IDDenominacionOrigen int NOT NULL,\r\n"
					+ "		CONSTRAINT fk_pais_datosvino FOREIGN KEY(IDPais) REFERENCES Paises(ID),\r\n"
					+ "		CONSTRAINT fk_denominacionOrigen_datosvino  FOREIGN KEY(IDDenominacionOrigen) REFERENCES DenominacionOrigen(ID),\r\n"
					+ "	);");
			conexionRepository.cerrarConexion(conexion);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
