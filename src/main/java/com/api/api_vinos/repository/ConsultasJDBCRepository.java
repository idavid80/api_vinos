package com.api.api_vinos.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.api.api_vinos.entity.Vino;

@Repository
public class ConsultasJDBCRepository {

	public ConexionRepository conexionRepository;
	private Connection conexion;
	ArrayList<Vino> listaDatos;
	ArrayList<String> listaNombre;
	ResultSet rs;
	
	public ArrayList<Vino> sacarDatosVino() {
		
		conexion = conexionRepository.abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			rs = stmt.executeQuery("Select * from Vinos;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexionRepository.cerrarConexion(conexion);
		
		return listaDatos;
	}
	
	public ArrayList<String> sacarVinosPorPais(String pais) {
		conexion = conexionRepository.abrirConexion();
		try {
			Statement stmt = conexion.createStatement();
			rs = stmt.executeQuery("Select Vinos.Nombre from Vinos "
							+ "inner join Paises on Vinos.IDPais = Paises.ID"
							+ "where Paises.Nombre = " + pais +";");
			
			while (rs.next()) {
				listaNombre.add(rs.getString("Nombre")); 
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexionRepository.cerrarConexion(conexion);
		
		return listaNombre;
	}
}
